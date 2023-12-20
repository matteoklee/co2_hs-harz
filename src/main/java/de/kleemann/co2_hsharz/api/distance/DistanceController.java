package de.kleemann.co2_hsharz.api.distance;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.distance.CoordinateService;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import lombok.NonNull;

/**
 * This API Controller offers the API Endpoint {@code /distance} which can be used to calculate the distance between two cities
 * 
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.11.2023
 */
@RestController
@RequestMapping("/api")
public class DistanceController {

    private final DistanceCalculationService distanceCalculationService;
    private final CoordinateService coordinateService;

    /**
     * Constructor with Auto-Wired services 
     * 
     * @param distanceCalculationService - {@link DistanceCalculationService}
     * @param coordinateService - {@link CoordinateService}
     */
    public DistanceController(DistanceCalculationService distanceCalculationService, CoordinateService coordinateService) {
        this.distanceCalculationService = distanceCalculationService;
        this.coordinateService = coordinateService;
    }

    /**
     * Returns the distance between two cities
     * 
     * @param start - NonNull Name of the starting city
     * @param end - NonNull Name of the end city
     */
    @GetMapping("/distance/{start}/{end}")
    public void getDistance(@NonNull @PathVariable(value = "start") String start, @NonNull @PathVariable(value = "end") String end) {
        System.err.println("CALLING /api/route ...");

        try {
            distanceCalculationService.calculateDistance(start, end);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
