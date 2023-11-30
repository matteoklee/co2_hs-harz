package de.kleemann.co2_hsharz.persistence.transport.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum TransportMediumName {

    DEFAULT(0, "Default"),
    CAR(1, "Pkw", "Car"),
    BUS_PUBLIC(2, "Buslinie", "BUS_PUBLIC"),
    BUS_TOUR(3, "Busreise", "BUS_TOUR"),
    TRAIN(4, "Zug", "Train"),
    BIKE(5, "Fahrrad", "Bike"),
    FOOT(6, "Fussgaenger", "Fuß", "Foot");

    private int transportMediumName;
    private String[] transportMediumNameStrings;

    private static final Map<String, TransportMediumName> NAME_MAP = new HashMap<>();

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

    TransportMediumName() {

    }

    TransportMediumName(int name, String ... nameStrings) {
        this.transportMediumName = name;
        this.transportMediumNameStrings = nameStrings;
    }

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

    public static Optional<TransportMediumName> valueOf(int name) {
        return Arrays.stream(values())
                .filter(element -> element.getTransportMediumName() == name)
                .findFirst();
    }

    public int getTransportMediumName() {
        return transportMediumName;
    }

    public String[] getTransprotMediumNameStrings() {
        return transportMediumNameStrings;
    }
}
