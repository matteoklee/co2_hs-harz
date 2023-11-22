package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

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

    public TransportMediumImportService(TransportMediumPersistenceService transportMediumPersistenceService)  {
        this.transportMediumPersistenceService = transportMediumPersistenceService;
    }

    public void importTransportMediumData() {
        File directory = new File(fileLocation);
        File[] directoryFiles = directory.listFiles();

        if(!directory.isDirectory()) {
            throw new CustomIllegalArgumentException("directory for transportMediums does not exists yet.");
        }

        for(File file : directoryFiles) {
            String fileName = file.getName();

            if(transportMediumPersistenceService.existsByTransportMediumFileName(fileName)) {
                continue;
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

        String[] split = filename.split("_");
        String name = split[0];

        if(name.equalsIgnoreCase("Bus")) {
            name = name + split[1];

            transportMediumEntity.setTransportMediumName(name);

            if(split[1].equalsIgnoreCase("Linie")) {
                String fuel = split[2];

                transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                transportMediumEntity.setTransportMediumSize(TransportMediumSize.DEFAULT);
                return transportMediumEntity;
            }

            // Bus Reise
            transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.DEFAULT);
            transportMediumEntity.setTransportMediumSize(TransportMediumSize.DEFAULT);
            return transportMediumEntity;
        }

        transportMediumEntity.setTransportMediumName(name);

        if(name.equalsIgnoreCase("Fahrrad")) {
            transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.DEFAULT);
            transportMediumEntity.setTransportMediumSize(TransportMediumSize.DEFAULT);
            return transportMediumEntity;
        }

        if(name.equalsIgnoreCase("Pkw")) {
            String fuel = split[1];

            if(fuel.equalsIgnoreCase("Otto")) {
                if(split[2].equalsIgnoreCase("LPG")) {
                    String size = split[3];

                    transportMediumEntity.setTransportMediumSize(TransportMediumSize.fromName(size));
                    transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.LPG);
                    return transportMediumEntity;
                }

                String size = split[2];

                transportMediumEntity.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                return transportMediumEntity;
            }

            if(fuel.equalsIgnoreCase("PHEV")) {
                fuel = fuel + "_" + split[2];
                String size = split[3];

                transportMediumEntity.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
                return transportMediumEntity;
            }

            if(fuel.equalsIgnoreCase("EM")) {
                String size = split[2];

                transportMediumEntity.setTransportMediumSize(TransportMediumSize.fromName(size));
                transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.ELECTRIC);
                return transportMediumEntity;
            }

            // Diesel
            String size = split[2];

            transportMediumEntity.setTransportMediumSize(TransportMediumSize.fromName(size));
            transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
            return transportMediumEntity;
        }

        if(name.equalsIgnoreCase("Zug")) {
            String fuel = split[3];

            transportMediumEntity.setTransportMediumFuel(TransportMediumFuel.fromName(fuel));
            transportMediumEntity.setTransportMediumSize(TransportMediumSize.DEFAULT);
            return transportMediumEntity;
        }
        return null;
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
                return -1;
            }

            return getCO2Value(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private double getCO2Value(String string) {
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
