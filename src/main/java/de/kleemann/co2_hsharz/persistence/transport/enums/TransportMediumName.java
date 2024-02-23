package de.kleemann.co2_hsharz.persistence.transport.enums;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;

/**
 * This enum represents all names, that can be used in a {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
public enum TransportMediumName {

    DEFAULT(0, "driving","Default"),
    CAR(1, "driving", "Pkw", "Car"),
    BUS_PUBLIC(2, "transit&transit_mode=bus", "Buslinie", "BUS_PUBLIC"),
    BUS_TOUR(3, "driving", "Busreise", "BUS_TOUR"),
    TRAIN(4, "transit&transit_mode=train", "Zug", "Train"),
    BIKE(5, "bicycling", "Fahrrad", "Bike"),
    FOOT(6, "walking", "Fussgaenger", "Fuß", "Foot");

    private int transportMediumName;
    private String transportMediumMode;
    private String[] transportMediumNameStrings;

    private static final Map<String, TransportMediumName> NAME_MAP = new HashMap<>();

    /**
     * Collects the enum values in a {@link Map}
     */
    static {
        for(TransportMediumName transportMediumName : values()) {
            String[] names = transportMediumName.getTransprotMediumNameStrings();
            if(names != null) {
                for(String name : names) {
                    NAME_MAP.put(name.toLowerCase(), transportMediumName);
                }
            }
        }
    }

    /**
     * Constructs a {@link TransportMediumName}
     * @param size {@link Integer} id
     * @param sizeString {@link String} {@link Array} aliases
     */
    TransportMediumName(int name, String mode, String ... nameStrings) {
        this.transportMediumName = name;
        this.transportMediumMode = mode;
        this.transportMediumNameStrings = nameStrings;
    }

    /**
     * Returns a {@link TransportMediumName} with this name
     * @param sizeString {@link String} id / name
     * @return {@link TransportMediumName}
     */
    public static TransportMediumName fromName(String nameString) {
        int name;
        try {
            name = Integer.parseInt(nameString);
            if(valueOf(name).isPresent()) {
                return valueOf(name).get();
            }
            return null;
        } catch (NumberFormatException e) {
            return NAME_MAP.get(nameString.toLowerCase());
        }
    }

    /**
     * Returns a possibly empty {@link Optional} of {@link TransportMediumName}s from an integer
     * @param fuel {@link Integer} id (ordinal)
     * @return {@link Optional} of {@link TransportMediumName}
     */
    public static Optional<TransportMediumName> valueOf(int name) {
        return Arrays.stream(values())
                .filter(element -> element.getTransportMediumName() == name)
                .findFirst();
    }

    /**
     * Returns the id of this {@link TransportMediumName}
     * @return id
     */
    public int getTransportMediumName() {
        return transportMediumName;
    }

    /**
     * Returns the Mode of this {@link TransportMediumName}
     * @return {@link String} mode
     */
    public String getTransportMediumMode() {
        return transportMediumMode;
    }

    /**
     * Returns a {@link Array} of aliases
     * @return {@link String} {@link Array} of aliases
     */
    public String[] getTransprotMediumNameStrings() {
        return transportMediumNameStrings;
    }
}
