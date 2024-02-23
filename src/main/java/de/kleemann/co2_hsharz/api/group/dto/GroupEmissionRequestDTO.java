package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEmissionRequestDTO {
    private String startLocation;
    private String endLocation;
    private TransportMediumDTO transportMediumDTO;
    private GroupEmissionDTO groupEmissionDTO;
}
