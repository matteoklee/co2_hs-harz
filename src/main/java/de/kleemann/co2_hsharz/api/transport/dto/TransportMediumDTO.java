package de.kleemann.co2_hsharz.api.transport.dto;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import lombok.Data;

/**
 * This Class is a Data Transfer Object (DTO) for the {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
@Data
public class TransportMediumDTO {

    private String transportMediumName;
    private String transportMediumSize;
    private String transportMediumFuel;
    private Double transportMediumFuelConsumption;


    public TransportMediumDTO(String transportMediumName, String transportMediumSize, String transportMediumFuel) {
        this.transportMediumName = transportMediumName;
        this.transportMediumSize = transportMediumSize;
        this.transportMediumFuel = transportMediumFuel;
    }
}
