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
        System.out.println("   ____ ___ ____       ____           _                     \n" +
                "  / ___/ _ \\___ \\     |  _ \\ ___  ___| |__  _ __   ___ _ __ \n" +
                " | |  | | | |__) |____| |_) / _ \\/ __| '_ \\| '_ \\ / _ \\ '__|\n" +
                " | |__| |_| / __/_____|  _ <  __/ (__| | | | | | |  __/ |   \n" +
                "  \\____\\___/_____|    |_| \\_\\___|\\___|_| |_|_| |_|\\___|_|   \n" +
                "                                                            \n" +
                "API successfully started.");
    }

    public static String getPREFIX() {
        return PREFIX;
    }
}
