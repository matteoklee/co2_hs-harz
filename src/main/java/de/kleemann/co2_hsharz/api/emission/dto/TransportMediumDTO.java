package de.kleemann.co2_hsharz.api.emission.dto;

/**
 * Class "TransportMediumDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
public class TransportMediumDTO {

    private String transportMediumName;
    private String transportMediumSize;
    private String transportMediumFuel;
    private String transportMediumCustomConsumption;

    public TransportMediumDTO(String transportMediumName, String transportMediumSize, String transportMediumFuel) {
        this.transportMediumName = transportMediumName;
        this.transportMediumSize = transportMediumSize;
        this.transportMediumFuel = transportMediumFuel;
    }

    public String getTransportMediumName() {
        return transportMediumName;
    }

    public void setTransportMediumName(String transportMediumName) {
        this.transportMediumName = transportMediumName;
    }

    public String getTransportMediumSize() {
        return transportMediumSize;
    }

    public void setTransportMediumSize(String transportMediumSize) {
        this.transportMediumSize = transportMediumSize;
    }

    public String getTransportMediumFuel() {
        return transportMediumFuel;
    }

    public void setTransportMediumFuel(String transportMediumFuel) {
        this.transportMediumFuel = transportMediumFuel;
    }
}
