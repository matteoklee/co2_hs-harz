package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController} API Endpoint
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

    /**
     * Default Constructor. Use Setter to insert values
     */
    public GroupEmissionRequestDTO() {

    }

    /**
     * Required Args Constructor
     * 
     * @param startLocation - {@link String} Name of the starting location
     * @param endLocation - {@link String} Name of the ending location
     * @param transportMediumDTO - {@link TransportMediumDTO} used transport medium
     * @param groupEmissionDTO - {@link GroupEmissionDTO} group
     */
    public GroupEmissionRequestDTO(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO, GroupEmissionDTO groupEmissionDTO) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportMediumDTO = transportMediumDTO;
        this.groupEmissionDTO = groupEmissionDTO;
    }

    /**
     * Getter for {@code GroupEmissionRequestDTO#startLocation}
     * @return {@link String} Name of the starting location
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     * Setter for {@code GroupEmissionRequestDTO#startLocation}
     * @param startLocation - New {@link String} startLocation
     */
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * Getter for {@code GroupEmissionRequestDTO#endLocation}
     * @return {@link String} Name of the ending location
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     * Setter for {@code GroupEmissionRequestDTO#endLocation}
     * @param endLocation - New {@link String} endLocation
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * Getter for {@code GroupEmissionRequestDTO#transportMediumDTO}
     * @return {@link TransportMediumDTO} used transport medium
     */
    public TransportMediumDTO getTransportMediumDTO() {
        return transportMediumDTO;
    }

    /**
     * Setter for {@code GroupEmissionRequestDTO#transportMediumDTO}
     * @param transportMediumDTO - New {@link TransportMediumDTO} transportMediumDTO
     */
    public void setTransportMediumDTO(TransportMediumDTO transportMediumDTO) {
        this.transportMediumDTO = transportMediumDTO;
    }

    /**
     * Getter for {@code GroupEmissionRequestDTO#groupEmissionDTO}
     * @return {@link GroupEmissionDTO} group
     */
    public GroupEmissionDTO getGroupEmissionDTO() {
        return groupEmissionDTO;
    }

    /**
     * Setter for {@code GroupEmissionRequestDTO#groupEmissionDTO}
     * @param groupEmissionDTO - New {@link GroupEmissionDTO} groupEmissionDTO
     */
    public void setGroupEmissionDTO(GroupEmissionDTO groupEmissionDTO) {
        this.groupEmissionDTO = groupEmissionDTO;
    }
}
