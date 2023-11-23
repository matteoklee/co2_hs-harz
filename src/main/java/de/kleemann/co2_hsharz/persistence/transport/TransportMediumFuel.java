package de.kleemann.co2_hsharz.persistence.transport;

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
    PETROL(1, "Otto"),
    DIESEL(2, "Diesel"),
    PHEV_DIESEL(3, "PHEV_Diesel"),
    PHEV_OTTO(4, "PHEV_Otto"),
    ELECTRIC(5, "Elektro"),
    CNG(6, "CNG"),
    LPG(7, "LPG");

    private int transportMediumFuel;
    private String transportMediumFuelString;

    private static final Map<String, TransportMediumFuel> FUELNAME_MAP = new HashMap<>();

    static {
        for(TransportMediumFuel fuel : values()) {
            if(fuel.getTransportMediumFuelString() != null) {
                FUELNAME_MAP.put(fuel.getTransportMediumFuelString().toLowerCase(), fuel);
            }
        }
    }

    TransportMediumFuel() {

    }

    TransportMediumFuel(int fuel, String fuelString) {
        this.transportMediumFuel = fuel;
        this.transportMediumFuelString = fuelString;
    }

    public String getTransportMediumFuelString() {
        return transportMediumFuelString;
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

    @Override
    public String toString() {
        //check what needed
        return transportMediumFuelString;
    }
}
