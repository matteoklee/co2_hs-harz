package de.kleemann.co2_hsharz.api.distance;

import de.kleemann.co2_hsharz.core.distance.CoordinateService;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
    private final CoordinateService coordinateService;

    public DistanceController(DistanceCalculationService distanceCalculationService, CoordinateService coordinateService) {
        this.distanceCalculationService = distanceCalculationService;
        this.coordinateService = coordinateService;
    }

    @GetMapping("/route")
    public void getDistance() {
        System.err.println("CALLING /api/route ...");
        //distanceCalculationService.routing();
        try {
            coordinateService.getCoordinatesFromCity("Berlin");
            coordinateService.getCoordinatesFromCity("Halle");
            coordinateService.getCoordinatesFromCity("Wettin-Löbejün");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
