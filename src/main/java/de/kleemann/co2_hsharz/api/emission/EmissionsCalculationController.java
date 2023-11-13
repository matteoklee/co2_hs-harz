package de.kleemann.co2_hsharz.api.emission;

import de.kleemann.co2_hsharz.api.emission.dto.EmissionsCalculationDTO;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import de.kleemann.co2_hsharz.core.emissions.EmissionsCalculationService;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Class "EmissionsCalculationController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@RestController
@RequestMapping("/api")
public class EmissionsCalculationController {

    private final EmissionsCalculationService emissionsCalculationService;
    private final TransportMediumService transportMediumService;
    private final DistanceCalculationService distanceCalculationService;

    public EmissionsCalculationController(EmissionsCalculationService emissionsCalculationService,
                                          TransportMediumService transportMediumService,
                                          DistanceCalculationService distanceCalculationService) {
        this.emissionsCalculationService = emissionsCalculationService;
        this.transportMediumService = transportMediumService;
        this.distanceCalculationService = distanceCalculationService;
    }

    @GetMapping("/emission")
    public double getEmissionsForRoute(@RequestBody EmissionsCalculationDTO emissionsCalculationDTO) {
        try {
            return emissionsCalculationService.calculateEmission(transportMediumService.findTransportMediumByName(emissionsCalculationDTO.getTransportMediumName()),
                    distanceCalculationService.calculateDistance(emissionsCalculationDTO.getStartLocation(), emissionsCalculationDTO.getEndLocation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
