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
    private TransportMediumDTO transportMediumDTO;

    public EmissionsCalculationDTO() {

    }

    public EmissionsCalculationDTO(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportMediumDTO = transportMediumDTO;
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

    public TransportMediumDTO getTransportMediumDTO() {
        return transportMediumDTO;
    }

    public void setTransportMediumDTO(TransportMediumDTO transportMediumDTO) {
        this.transportMediumDTO = transportMediumDTO;
    }
}
