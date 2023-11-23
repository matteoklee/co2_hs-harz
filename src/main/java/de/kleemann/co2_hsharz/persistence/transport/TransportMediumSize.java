package de.kleemann.co2_hsharz.persistence.transport;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum TransportMediumSize {

    DEFAULT(0, "default"),
    SMALL(1, "klein"),
    MEDIUM(2, "mittel"),
    LARGE(3, "gross");

    private int transportMediumSize;
    private String transportMediumSizeString;

    private static final Map<String, TransportMediumSize> SIZENAME_MAP = new HashMap<>();

    static {
        for(TransportMediumSize size : values()) {
            if(size.getTransportMediumSizeString() != null) {
                SIZENAME_MAP.put(size.getTransportMediumSizeString().toLowerCase(), size);
            }
        }
    }

    TransportMediumSize() {

    }

    TransportMediumSize(int size) {
        this.transportMediumSize = size;
    }

    TransportMediumSize(int size, String sizeString) {
        this.transportMediumSize = size;
        this.transportMediumSizeString = sizeString;
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

    public String getTransportMediumSizeString() {
        return transportMediumSizeString;
    }

    public static Optional<TransportMediumSize> valueOf(int size) {
        return Arrays.stream(values())
                .filter(element -> element.transportMediumSize == size)
                .findFirst();
    }

    @Override
    public String toString() {
        //check what needed for repository
        return transportMediumSizeString;
    }
}
