package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.transport.TransportMediumType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Arrays;

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
            System.err.println("No directory");
            return;
        }

        for(File file : directoryFiles) {
            String fileName = file.getName();
            //System.out.println("File: " + fileName + " with type: " + transportMediumType);

            TransportMediumEntity newTransportMedium = transportMediumPersistenceService.createTransportMediumEntity();
            TransportMediumType transportMediumType = TransportMediumType.fromFileName(fileName);

            if(transportMediumType == null) continue;

            newTransportMedium.setTransportName(transportMediumType.getFileNamePart());
            newTransportMedium.setTransportMediumType(transportMediumType);
            newTransportMedium.setConsumption(getCO2FromFile(file.getAbsolutePath()));
            TransportMediumEntity persistedTransportMedium = transportMediumPersistenceService.persistTransportMedium(newTransportMedium);
            System.out.println("Created new transport medium: " + persistedTransportMedium.getTransportId() + ", name: " + persistedTransportMedium.getTransportName()
            + ", type: " + persistedTransportMedium.getTransportMediumType() + ", consumption: " + persistedTransportMedium.getConsumption());
        }
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
