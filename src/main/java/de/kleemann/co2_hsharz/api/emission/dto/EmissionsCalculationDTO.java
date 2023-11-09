package de.kleemann.co2_hsharz.api.emission.dto;

/**
 * Class "EmissionsCalculationDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class EmissionsCalculationDTO {

    private String startLocation;
    private String endLocation;
    private String transportMediumName;
    private String transportMediumType;
    private String transportMediumFuel;

    public EmissionsCalculationDTO() {

    }

    public EmissionsCalculationDTO(String startLocation, String endLocation, String transportMediumName, String transportMediumType, String transportMediumFuel) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportMediumName = transportMediumName;
        this.transportMediumType = transportMediumType;
        this.transportMediumFuel = transportMediumFuel;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getTransportMediumName() {
        return transportMediumName;
    }

    public void setTransportMediumName(String transportMediumName) {
        this.transportMediumName = transportMediumName;
    }

    public String getTransportMediumType() {
        return transportMediumType;
    }

    public void setTransportMediumType(String transportMediumType) {
        this.transportMediumType = transportMediumType;
    }

    public String getTransportMediumFuel() {
        return transportMediumFuel;
    }

    public void setTransportMediumFuel(String transportMediumFuel) {
        this.transportMediumFuel = transportMediumFuel;
    }
}
