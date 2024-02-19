package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;

/**
 * Class "GroupEmissionDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupEmissionRequestDTO {

    private String startLocation;
    private String endLocation;
    private TransportMediumDTO transportMediumDTO;
    private GroupEmissionDTO groupEmissionDTO;

    public GroupEmissionRequestDTO() {

    }

    public GroupEmissionRequestDTO(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO, GroupEmissionDTO groupEmissionDTO) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportMediumDTO = transportMediumDTO;
        this.groupEmissionDTO = groupEmissionDTO;
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

    public GroupEmissionDTO getGroupEmissionDTO() {
        return groupEmissionDTO;
    }

    public void setGroupEmissionDTO(GroupEmissionDTO groupEmissionDTO) {
        this.groupEmissionDTO = groupEmissionDTO;
    }
}
