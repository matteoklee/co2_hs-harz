package de.kleemann.co2_hsharz.api.emission;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.api.emission.dto.EmissionsCalculationRequestDTO;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import de.kleemann.co2_hsharz.core.emissions.EmissionsCalculationService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;

/**
 * This API Controller offers the API Endpoint {@code /emission} which can be used to calculate the amount of co2 emitted on a route between two cities.
 * <br> See the API Documentation at <a href=https://github.com/matteoklee/co2_hs-harz/wiki/Emission-Endpoint> Github </a> for detailed information
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

    /**
     * Constructor with Auto-Wired required Services
     * 
     * @param emissionsCalculationService - {@link EmissionsCalculationService}
     * @param transportMediumService - {@link TransportMediumService}
     * @param distanceCalculationService - {@link DistanceCalculationService}
     */
    public EmissionsCalculationController(EmissionsCalculationService emissionsCalculationService,
                                          TransportMediumService transportMediumService,
                                          DistanceCalculationService distanceCalculationService) {
        this.emissionsCalculationService = emissionsCalculationService;
        this.transportMediumService = transportMediumService;
        this.distanceCalculationService = distanceCalculationService;
    }

    /**
     * Returns the amount of CO2 emitted when traveling on a given route on given conditions
     * 
     * @param emissionsCalculationRequestDTO - {@link EmissionsCalculationRequestDTO} containing the start, end location and vehicle
     * @return {@code double} CO2-Emission
     */
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
            distance = distanceCalculationService.calculateDistance(emissionsCalculationRequestDTO.getStartLocation(),
                    emissionsCalculationRequestDTO.getEndLocation(), transportMediumDTO);
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
