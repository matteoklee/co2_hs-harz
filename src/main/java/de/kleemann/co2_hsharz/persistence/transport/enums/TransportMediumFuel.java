package de.kleemann.co2_hsharz.persistence.transport.enums;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;

/**
 * This enum represents all fuel types, that can be used in a {@link TransportMedium}
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

    /**
     * Collects the enum values in a {@link Map}
     */
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

    /**
     * Constructs a {@link TransportMediumFuel}
     * @param fuel {@link Integer} id
     * @param fuelString {@link String} {@link Array} aliases
     */
    TransportMediumFuel(int fuel, String ... fuelString) {
        this.transportMediumFuel = fuel;
        this.transportMediumFuelStrings = fuelString;
    }

    /**
     * Returns a {@link Array} all valid names
     * @return {@link Array} of all aliases
     */
    public String[] getTransportMediumFuelStrings() {
        return transportMediumFuelStrings;
    }

    /**
     * Returns a {@link TransportMediumFuel} with this name
     * @param fuelName {@link String} name
     * @return {@link TransportMedium} with this name
     */
    public static TransportMediumFuel fromName(String fuelName) {
        int fuel;
        try {
            fuel = Integer.parseInt(fuelName);
            return valueOf(fuel).get();
        } catch (NumberFormatException exception) {
            return FUELNAME_MAP.get(fuelName.toLowerCase());
        }
    }

    /**
     * Returns a possibly empty {@link Optional} of {@link TransportMediumFuel}s from a integer
     * @param fuel {@link Integer} id (ordinal)
     * @return {@link Optional} of {@link TransportMediumFuel}
     */
    public static Optional<TransportMediumFuel> valueOf(int fuel) {
        return Arrays.stream(values())
                .filter(element -> element.transportMediumFuel == fuel)
                .findFirst();
    }

}
