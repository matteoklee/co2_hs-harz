package de.kleemann.co2_hsharz.api.distance;

import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class "DistanceController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.11.2023
 */
@RestController
@RequestMapping("/api")
public class DistanceController {

    private final DistanceCalculationService distanceCalculationService;

    public DistanceController(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    @GetMapping("/route")
    public double getDistance() {
        System.err.println("CALCULATING ROUTE...");
        return distanceCalculationService.calculate();
    }

}
