package de.kleemann.co2_hsharz.persistence.transport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * Class "TransportMediumImportService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 13.11.2023
 */
@Service
public class TransportMediumImportService {

    private final TransportMediumPersistenceService transportMediumPersistenceService;
    @Value("${data.import.location}")
    private String fileLocation;

    @Value("${data.import.version}")
    private double transportMediumVersion;

    public TransportMediumImportService(TransportMediumPersistenceService transportMediumPersistenceService)  {
        this.transportMediumPersistenceService = transportMediumPersistenceService;
    }

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

    private TransportMediumEntity handleTrain(TransportMediumEntity transportMedium, String[] split) {
        String fuel = split[3];

        transportMedium.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
        transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        return transportMedium;
    }

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

    private TransportMediumEntity handleBike(TransportMediumEntity transportMedium, String[] split) {
        transportMedium.setTransportMediumFuel(TransportMediumFuel.DEFAULT);
        transportMedium.setTransportMediumSize(TransportMediumSize.DEFAULT);
        return transportMedium;
    }

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
