package de.kleemann.co2_hsharz;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumImportService;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "de.kleemann.co2_hsharz.*")
public class Application {

    private static String PREFIX = "[CO2_HS-Harz] ";

    private final TransportMediumImportService transportMediumImportService;

    public Application(TransportMediumImportService transportMediumImportService) {
        this.transportMediumImportService = transportMediumImportService;
        this.transportMediumImportService.importTransportMediumData();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(PREFIX + "API successfully started.");

    }

    public static String getPREFIX() {
        return PREFIX;
    }
}
