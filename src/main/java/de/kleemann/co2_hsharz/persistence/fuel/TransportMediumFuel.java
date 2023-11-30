package de.kleemann.co2_hsharz.persistence.fuel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class "TransportMediumFuel" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
public enum TransportMediumFuel {

    DEFAULT(0, "Default"),
    PETROL(1, "Otto", "Petrol", "Benzin"),
    DIESEL(2, "Diesel"),
    PHEV_DIESEL(3, "PHEV_Diesel"),
    PHEV_OTTO(4, "PHEV_Otto"),
    ELECTRIC(5, "Elektro", "Electric"),
    CNG(6, "Erdgas", "CNG"),
    LPG(7, "Autogas", "LPG");

    private int transportMediumFuel;
    private String[] transportMediumFuelStrings;

    private static final Map<String, TransportMediumFuel> FUELNAME_MAP = new HashMap<>();

    static {
        for(TransportMediumFuel transportMediumFuel : values()) {
            String[] fuelNames = transportMediumFuel.getTransportMediumFuelStrings();
            if(fuelNames != null) {
                for(String fuel : fuelNames) {
                    FUELNAME_MAP.put(fuel.toLowerCase(), transportMediumFuel);
                }
            }
        }
    }

    TransportMediumFuel() {

    }

    TransportMediumFuel(int fuel, String ... fuelString) {
        this.transportMediumFuel = fuel;
        this.transportMediumFuelStrings = fuelString;
    }

    public String[] getTransportMediumFuelStrings() {
        return transportMediumFuelStrings;
    }

    public static TransportMediumFuel fromName(String fuelName) {
        int fuel;
        try {
            fuel = Integer.parseInt(fuelName);
            return valueOf(fuel).get();
        } catch (NumberFormatException exception) {
            return FUELNAME_MAP.get(fuelName.toLowerCase());
        }
    }

    public static Optional<TransportMediumFuel> valueOf(int fuel) {
        return Arrays.stream(values())
                .filter(element -> element.transportMediumFuel == fuel)
                .findFirst();
    }

}
