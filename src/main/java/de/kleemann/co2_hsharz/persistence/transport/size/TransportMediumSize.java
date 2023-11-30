package de.kleemann.co2_hsharz.persistence.transport.size;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum TransportMediumSize {

    DEFAULT(0, "default"),
    SMALL(1, "klein", "small"),
    MEDIUM(2, "mittel", "medium"),
    LARGE(3, "gross", "large");

    private int transportMediumSize;
    private String[] transportMediumSizeStrings;

    private static final Map<String, TransportMediumSize> SIZENAME_MAP = new HashMap<>();

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

    TransportMediumSize() {

    }

    TransportMediumSize(int size) {
        this.transportMediumSize = size;
    }

    TransportMediumSize(int size, String ... sizeString) {
        this.transportMediumSize = size;
        this.transportMediumSizeStrings = sizeString;
    }

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

    public String[] getTransportMediumSizeString() {
        return transportMediumSizeStrings;
    }

    public static Optional<TransportMediumSize> valueOf(int size) {
        return Arrays.stream(values())
                .filter(element -> element.transportMediumSize == size)
                .findFirst();
    }

}
