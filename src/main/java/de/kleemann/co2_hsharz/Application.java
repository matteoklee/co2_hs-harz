package de.kleemann.co2_hsharz;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumImportService;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.kleemann.co2_hsharz.*")
public class Application {

    private static String PREFIX = "[CO2_HS-Harz] ";

    private final TransportMediumImportService transportMediumImportService;
    private final TransportMediumPersistenceService transportMediumPersistenceService;

    public Application(TransportMediumImportService transportMediumImportService, TransportMediumPersistenceService transportMediumPersistenceService) {
        this.transportMediumImportService = transportMediumImportService;
        this.transportMediumPersistenceService = transportMediumPersistenceService;
        //nur einmaliger Import
        //transportMediumImportService.importTransportMediumData();
        this.transportMediumPersistenceService.findAllTransportMediums()
                .forEach((transportMediumEntity -> System.out.println("Id: " + transportMediumEntity.getTransportId()
                        + ", Name: " + transportMediumEntity.getTransportName()
                        + ", Type: " + transportMediumEntity.getTransportMediumType()
                        + ", Consumption: " + transportMediumEntity.getConsumption())));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(PREFIX + "API successfully started.");

    }

    public static String getPREFIX() {
        return PREFIX;
    }
}
