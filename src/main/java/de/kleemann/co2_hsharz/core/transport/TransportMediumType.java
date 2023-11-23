package de.kleemann.co2_hsharz.core.transport;

import java.util.HashMap;
import java.util.Map;

public enum TransportMediumType {

    CAR_PETROL_SMALL("Pkw_Otto_klein"),
    CAR_PETROL_MEDIUM("Pkw_Otto_mittel"),
    CAR_PETROL_LARGE("Pkw_Otto_gross"),
    CAR_DIESEL_SMALL("Pkw_Diesel_klein"),
    CAR_DIESEL_MEDIUM("Pkw_Diesel_mittel"),
    CAR_DIESEL_LARGE("Pkw_Diesel_gross"),
    BIKE("Fahrrad"),
    FOOT,
    TRAIN_DIESEL("Zug_Personen_Fern_Diesel"),
    TRAIN_ELECTRIC("Zug_Personen_Fern_Elektro"),
    CAR_HYBRID_DIESEL_SMALL("Pkw_PHEV_Diesel_klein"),
    CAR_HYBRID_DIESEL_MEDIUM("Pkw_PHEV_Diesel_mittel"),
    CAR_HYBRID_DIESEL_LARGE("Pkw_PHEV_Diesel_gross"),
    CAR_HYBRID_OTTO_SMALL("Pkw_PHEV_Otto_klein"),
    CAR_HYBRID_OTTO_MEDIUM("Pkw_PHEV_Otto_mittel"),
    CAR_HYBRID_OTTO_LARGE("Pkw_PHEV_Otto_gross"),
    CAR_ELECTRIC_SMALL("Pkw_EM_klein"),
    CAR_ELECTRIC_MEDIUM("Pkw_EM_mittel"),
    BUS_LINE_CNG("Bus_Linie_CNG"),
    BUS_LINE_DIESEL("Bus_Linie_Diesel"),
    BUS_TOUR("Bus_Reise"),
    CAR_CNG_OTTO("Pkw_Otto_LPG_mittel"); //Erdgas


    private static final Map<String, TransportMediumType> FILENAME_MAP = new HashMap<>();

    static {
        for (TransportMediumType type : values()) {
            if (type.fileNamePart != null) {
                FILENAME_MAP.put(type.fileNamePart.toLowerCase(), type);
            }
        }
    }

    private String fileNamePart;

    TransportMediumType() {

    }

    TransportMediumType(String fileNamePart) {
        this.fileNamePart = fileNamePart.toLowerCase();
    }

    public static TransportMediumType fromFileName(String fileName) {
        String fileNameLower = fileName.toLowerCase();
        for (Map.Entry<String, TransportMediumType> entry : FILENAME_MAP.entrySet()) {
            if (fileNameLower.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }


    public String getFileNamePart() {
        return fileNamePart;
    }
}
