package de.kleemann.co2_hsharz.persistence.transport.enums;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;

/**
 * This enum represents all fuel sizes, that can be used in a {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
public enum TransportMediumSize {

    DEFAULT(0, "default"),
    SMALL(1, "klein", "small"),
    MEDIUM(2, "mittel", "medium"),
    LARGE(3, "gross", "large");

    private int transportMediumSize;
    private String[] transportMediumSizeStrings;

    private static final Map<String, TransportMediumSize> SIZENAME_MAP = new HashMap<>();

    /**
     * Collects the enum values in a {@link Map}
     */
    static {
        for(TransportMediumSize transportMediumSize : values()) {
            String[] sizeNames = transportMediumSize.getTransportMediumSizeString();
            if(sizeNames != null) {
                for(String size : sizeNames) {
                    SIZENAME_MAP.put(size.toLowerCase(), transportMediumSize);
                }
            }
        }
    }

    /**
     * Constructs a {@link TransportMediumSize}
     * @param size {@link Integer} id
     * @param sizeString {@link String} {@link Array} aliases
     */
    TransportMediumSize(int size, String ... sizeString) {
        this.transportMediumSize = size;
        this.transportMediumSizeStrings = sizeString;
    }

    /**
     * Returns a {@link TransportMediumSize} with this name
     * @param sizeString {@link String} id / name
     * @return {@link TransportMediumSize}
     */
    public static TransportMediumSize fromName(String sizeString) {
        int size;
        try {
            size = Integer.parseInt(sizeString);
            return valueOf(size).get();
        } catch (NumberFormatException exception) {
            return SIZENAME_MAP.get(sizeString.toLowerCase());
        }
        //return TransportMediumSize.valueOf(sizeString);
    }

    /**
     * Returns a {@link Array} of aliases
     * @return {@link String} {@link Array} of aliases
     */
    public String[] getTransportMediumSizeString() {
        return transportMediumSizeStrings;
    }

    /**
     * Returns a possibly empty {@link Optional} of {@link TransportMediumSize}s from an integer
     * @param fuel {@link Integer} id (ordinal)
     * @return {@link Optional} of {@link TransportMediumSize}
     */
    public static Optional<TransportMediumSize> valueOf(int size) {
        return Arrays.stream(values())
                .filter(element -> element.transportMediumSize == size)
                .findFirst();
    }

}
