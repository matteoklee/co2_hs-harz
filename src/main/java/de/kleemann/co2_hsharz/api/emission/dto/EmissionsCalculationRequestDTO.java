package de.kleemann.co2_hsharz.api.emission.dto;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;

/**
 * Class "EmissionsCalculationDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class EmissionsCalculationRequestDTO {

    private String startLocation;
    private String endLocation;
    private TransportMediumDTO transportMediumDTO;

    public EmissionsCalculationRequestDTO() {

    }

    public EmissionsCalculationRequestDTO(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO) {
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
