package de.kleemann.co2_hsharz.api.emission.dto;

import de.kleemann.co2_hsharz.api.emission.EmissionsCalculationController;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;

/**
 * This Class is a Data Transfer Object for the {@link EmissionsCalculationController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class EmissionsCalculationRequestDTO {

    private String startLocation;
    private String endLocation;
    private TransportMediumDTO transportMediumDTO;

    /**
     * Default Constructor. Use Setters to insert values
     */
    public EmissionsCalculationRequestDTO() {

    }

    /**
     * Required Args Constructor
     * 
     * @param startLocation - {@link String} Name of the starting location
     * @param endLocation - {@link String} Name of the ending location
     * @param transportMediumDTO - {@link TransportMediumDTO} Used TransportMedium
     */
    public EmissionsCalculationRequestDTO(String startLocation, String endLocation, TransportMediumDTO transportMediumDTO) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.transportMediumDTO = transportMediumDTO;
    }

    /**
     * Getter for {@code EmissionsCalculationRequestDTO#startLocation}
     * @return {@link String} StartLocation of this EmissionCalculationRequest
     */
    public String getStartLocation() {
        return startLocation;
    }

    /**
     * Setter for {@code EmissionsCalculationRequestDTO#startLocation}
     * @param startLocation - New {@link String} StartLocation of this EmissionCalculationRequest
     */
    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    /**
     * Getter for {@code EmissionsCalculationRequestDTO#endLocation}
     * @return {@link String} EndLocation of this EmissionCalculationRequest
     */
    public String getEndLocation() {
        return endLocation;
    }

    /**
     * Setter for {@code EmissionsCalculationRequestDTO#endLocation}
     * @param endLocation - New {@link String} EndLocation of this EmissionCalculationRequest
     */
    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    /**
     * Getter for {@code EmissionsCalculationRequestDTO#transportMediumDTO}
     * @return {@link TransportMediumDTO} TransportMediumDTO of this EmissionCalculationRequest
     */
    public TransportMediumDTO getTransportMediumDTO() {
        return transportMediumDTO;
    }

    /**
     * Setter for {@code EmissionsCalculationRequestDTO#transportMediumDTO}
     * @param transportMediumDTO - New {@link TransportMediumDTO} of this EmissionCalculationRequest
     */
    public void setTransportMediumDTO(TransportMediumDTO transportMediumDTO) {
        this.transportMediumDTO = transportMediumDTO;
    }
}
