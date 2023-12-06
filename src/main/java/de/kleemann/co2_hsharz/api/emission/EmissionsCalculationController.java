package de.kleemann.co2_hsharz.api.emission;

import de.kleemann.co2_hsharz.api.emission.dto.EmissionsCalculationRequestDTO;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import de.kleemann.co2_hsharz.core.emissions.EmissionsCalculationService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/emission")
    public double getEmissionsForRoute(@RequestBody EmissionsCalculationRequestDTO emissionsCalculationRequestDTO) {
        //todo check inputs -->
        String startLocation = emissionsCalculationRequestDTO.getStartLocation();
        String endLocation = emissionsCalculationRequestDTO.getStartLocation();
        if(startLocation == null || endLocation == null || startLocation.isEmpty() || endLocation.isEmpty()) {
            throw new CustomIllegalArgumentException("startLocation and endLocation must not be null.");
        }
        TransportMediumDTO transportMediumDTO = emissionsCalculationRequestDTO.getTransportMediumDTO();
        if(transportMediumDTO == null) {
            throw new CustomIllegalArgumentException("transportMediumDTO must not be null.");
        }

        double distance = 0;
        try {
            distance = distanceCalculationService.calculateDistance(emissionsCalculationRequestDTO.getStartLocation(), emissionsCalculationRequestDTO.getEndLocation());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(transportMediumDTO.getTransportMediumFuelConsumption() != null) {
            return emissionsCalculationService.calculateCustomEmission(transportMediumDTO, distance);
        }

        TransportMediumImpl transportMedium = null;
        transportMedium = transportMediumService.findTransportMediumByCustomInput(transportMediumDTO);

        return emissionsCalculationService.calculateEmission(transportMedium, distance);
    }

}
