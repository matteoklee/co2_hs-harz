package de.kleemann.co2_hsharz.api.emission.dto;

import de.kleemann.co2_hsharz.api.emission.EmissionsCalculationController;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class is a Data Transfer Object for the {@link EmissionsCalculationController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmissionsCalculationRequestDTO {
    private String startLocation;
    private String endLocation;
    private TransportMediumDTO transportMediumDTO;
}
