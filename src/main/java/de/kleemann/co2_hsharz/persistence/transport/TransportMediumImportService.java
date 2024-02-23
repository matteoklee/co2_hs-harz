package de.kleemann.co2_hsharz.persistence.transport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This Service is used to import {@link TransportMedium}s from a set of files located in the data folder.
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 13.11.2023
 */
@Service
public class TransportMediumImportService {

	/** {@link TransportMediumPersistenceService} used to save the {@link TransportMediumEntity}s */
    private final TransportMediumPersistenceService transportMediumPersistenceService;
    
    /** {@link String} path to data folder - Defined in Application Properties */
    @Value("${data.import.location}")
    private String fileLocation;

    /** {@link Double} data version - Defined in Application Properties */
    @Value("${data.import.version}")
    private double transportMediumVersion;

    /**
     * Constructs a {@link TransportMediumImportService}
     * @param transportMediumPersistenceService - {@link TransportMediumPersistenceService} used to save the {@link TransportMediumEntity}s
     */
    public TransportMediumImportService(TransportMediumPersistenceService transportMediumPersistenceService)  {
        this.transportMediumPersistenceService = transportMediumPersistenceService;
    }

    /**
     * Imports all available {@link TransportMedium}s to database. <br>
     */
    public void importTransportMediumData() {
        File directory = new File(fileLocation);
        File[] directoryFiles = directory.listFiles();

        if(!directory.isDirectory()) {
            throw new CustomIllegalArgumentException("directory for transportMediums does not exists yet. Path: " + directory.getAbsolutePath());
        }

        for(File file : directoryFiles) {
            String fileName = file.getName();

            if(transportMediumPersistenceService.existsByTransportMediumFileName(fileName)) {
                TransportMediumEntity existingTransportMedium = transportMediumPersistenceService.findTransportMediumByFileName(fileName);
                double existingVersion = existingTransportMedium.getTransportMediumVersion();
                if(existingVersion >= transportMediumVersion) {
                    continue;
                }
            }
            double emission = getCO2FromFile(file.getAbsolutePath());
            TransportMediumEntity newTransportMedium = getTransportMedium(fileName);
            newTransportMedium.setTransportMediumConsumption(emission);

            //newTransportMedium.setConsumption(getCO2FromFile(file.getAbsolutePath()));
            TransportMediumEntity persistedTransportMedium = transportMediumPersistenceService.persistTransportMedium(newTransportMedium);
            System.out.println("Created new transport medium: " + persistedTransportMedium.toString());
        }
    }

    /**
     * Returns a {@link TransportMedium} constructed from a data file. <br>
     * This Method will parse the filename and determines what {@link TransportMediumName} this {@link TransportMedium} uses. <br>
     * It then switches method depending on the {@link TransportMediumName}
     * @param filename {@link String} name of the file
     * @return {@link TransportMediumEntity} of this file
     */
    private TransportMediumEntity getTransportMedium(String filename) {
        TransportMediumEntity transportMediumEntity = transportMediumPersistenceService.createTransportMediumEntity();
        transportMediumEntity.setTransportMediumFileName(filename);
        transportMediumEntity.setTransportMediumVersion(transportMediumVersion);

        String[] split = filename.split("_");
        String name = split[0];

        if(name.toLowerCase().equals("bus")) {
            name += split[1];
        }
        TransportMediumName transportMediumName = TransportMediumName.fromName(name);
        if(transportMediumName == null) {
        	throw new CustomIllegalArgumentException("failed to find transportMediumName from String " + name);
        }
        transportMediumEntity.setTransportMediumName(transportMediumName);

        switch(transportMediumName) {
            case DEFAULT, FOOT:
            case BIKE:
                return handleBike(transportMediumEntity, split);
            case BUS_PUBLIC, BUS_TOUR:
                return handleBus(transportMediumEntity, split);
            case CAR:
                return handleCar(transportMediumEntity, split);
            case TRAIN:
                return handleTrain(transportMediumEntity, split);
            default:
                throw new CustomIllegalArgumentException("transportMedium could not be created from file.");
        }

    }

    /**
     * Handles Import for a car
     * @param transportMedium {@link TransportMediumEntity}
     * @param split {@link String} {@link Array} parsed filename
     * @return {@link TransportMediumEntity} containing the specifications from filename
     */
    private TransportMediumEntity handleCar(TransportMediumEntity transportMedium, String[] split) {
        String fuel = split[1];
        String size = "";

        switch(fuel.toLowerCase()) {
            case "otto":
                if(split[2].equalsIgnoreCase("LPG")) {
                    size = split[3];

                    transportMedium.setTransportMediumSize(TransportMediumSize.fromName(size));
                    transportMedium.setTransportMediumFuel(TransportMediumFuel.LPG);
                } else {
                    size = split[2];

                    transportMedium.setTransportMediumSize(TransportMediumSize.fromName(size));
                    transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                }
                return transportMedium;
            case "diesel":
                size = split[2];

                transportMedium.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                return transportMedium;
            case "phev":
                fuel = fuel + "_" + split[2];
                size = split[3];

                transportMedium.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                return transportMedium;
            case "em":
                size = split[2];

                transportMedium.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMedium.setTransportMediumFuel(TransportMediumFuel.ELECTRIC);
                return transportMedium;
            default:
                throw new CustomIllegalArgumentException("car combination could not be found.");
        }
    }

    /**
     * Handles Import for a train
     * @param transportMedium {@link TransportMediumEntity}
     * @param split {@link String} {@link Array} parsed filename
     * @return {@link TransportMediumEntity} containing the specifications from filename
     */
    private TransportMediumEntity handleTrain(TransportMediumEntity transportMedium, String[] split) {
        String fuel = split[3];

        transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
        transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        return transportMedium;
    }

    /**
     * Handles Import for a bus
     * @param transportMedium {@link TransportMediumEntity}
     * @param split {@link String} {@link Array} parsed filename
     * @return {@link TransportMediumEntity} containing the specifications from filename
     */
    private TransportMediumEntity handleBus(TransportMediumEntity transportMedium, String[] split) {

        if(split[1].equalsIgnoreCase("Linie")) {
            String fuel = split[2];

            transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
            transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        } else {
            transportMedium.setTransportMediumFuel(TransportMediumFuel.DEFAULT);
            transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        }
        return transportMedium;
    }

    /**
     * Handles Import for a bike
     * @param transportMedium {@link TransportMediumEntity}
     * @param split {@link String} {@link Array} parsed filename
     * @return {@link TransportMediumEntity} containing the specifications from filename
     */
    private TransportMediumEntity handleBike(TransportMediumEntity transportMedium, String[] split) {
        transportMedium.setTransportMediumFuel(TransportMediumFuel.DEFAULT);
        transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        return transportMedium;
    }

    /**
     * Reads the CO2 Emission per km from the file
     * @param path {@link String} path to file
     * @return {@link Double} CO2 Emission per km
     */
    private double getCO2FromFile(String path) {
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String string;
            boolean direct_emission = true;
            while((string = bufferedReader.readLine()) != null) {
                if(string.startsWith("<td>CO2</td>")) {
                    string = bufferedReader.readLine();

                    if(string.equals("<td>&nbsp;</td>")) { // Keine direkten Emissionen
                        direct_emission = false;
                    }
                    break;
                }
            }
            bufferedReader.close();
            fileReader.close();
            if(!direct_emission) {
                return 0;
            }
            return getCO2Value(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Parses a string to co2 emission value
     * @param string {@link String} from file
     * @return {@link Double} co2 emission per km
     */
    private double getCO2Value(String string) {
    	if(string == null || string.length() < 34)
    		return -1;
    	
        string = string.substring(18, 34);
        String[] split = string.split("<sup>");

        String string_number = split[0];
        int exponent = Integer.parseInt(split[1]);

        String[] number_and_base = string_number.split("\\*");
        double number = Double.parseDouble(number_and_base[0].replace(',', '.'));
        double base = Double.parseDouble(number_and_base[1]);

        return BigDecimal.valueOf(number).multiply(BigDecimal.valueOf(Math.pow(base, exponent))).doubleValue();
    }

}
