package de.kleemann.co2_hsharz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumImportService;

/**
 * Main Application Class
 */
@SpringBootApplication(scanBasePackages = "de.kleemann.co2_hsharz.*")
public class Application {

	/**
	 * {@link String} Logging Prefix
	 */
    private static String PREFIX = "[CO2_HS-Harz] ";

    /**
     * {@link TransportMediumImportService} <br>
     * Will be executed upon startup to import data files
     */
    private final TransportMediumImportService transportMediumImportService;

    /**
     * Constructs a new {@link Application} <br>
     * @param transportMediumImportService - {@link TransportMediumImportService}-Bean
     */
    public Application(TransportMediumImportService transportMediumImportService) {
        this.transportMediumImportService = transportMediumImportService;
        this.transportMediumImportService.importTransportMediumData();
    }

    /**
     * Main Method, starting Spring Application
     * @param args - {@link String}[] Command-line arguments
     */
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

    /**
     * Returns the logging prefix of this Application
     * @return {@link String} Prefix
     */
    public static String getPREFIX() {
        return PREFIX;
    }
}
