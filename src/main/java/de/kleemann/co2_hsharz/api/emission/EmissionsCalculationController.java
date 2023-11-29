package de.kleemann.co2_hsharz.api.emission;

import de.kleemann.co2_hsharz.api.emission.dto.EmissionsCalculationDTO;
import de.kleemann.co2_hsharz.api.emission.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.distance.DistanceCalculationService;
import de.kleemann.co2_hsharz.core.emissions.EmissionsCalculationService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumSize;
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
    public double getEmissionsForRoute(@RequestBody EmissionsCalculationDTO emissionsCalculationDTO) {
        //todo check inputs -->
        TransportMediumDTO transportMediumDTO = emissionsCalculationDTO.getTransportMediumDTO();
        //TransportMediumImpl transportMedium = findTransportMediumByCustomInput(transportMediumDTO);
        TransportMediumImpl transportMedium = transportMediumService.findTransportMediumByNameAndSizeAndFuel(transportMediumDTO.getTransportMediumName(),
                TransportMediumSize.fromName(transportMediumDTO.getTransportMediumSize()),
                TransportMediumFuel.fromName(transportMediumDTO.getTransportMediumFuel()));
        double distance = 0;
        try {
            distance = distanceCalculationService.calculateDistance(emissionsCalculationDTO.getStartLocation(), emissionsCalculationDTO.getEndLocation());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return emissionsCalculationService.calculateEmission(transportMedium, distance);

    }

    //muss ich eigentlich nicht machen, wenn ich nach name size and fuel suche und default übergebe,
    // d.h. im frontend default parameter übergeben!
    private TransportMediumImpl findTransportMediumByCustomInput(TransportMediumDTO transportMediumDTO) {
        if(transportMediumDTO.getTransportMediumName() == null || transportMediumDTO.getTransportMediumName().isBlank()
                || transportMediumDTO.getTransportMediumName().isEmpty()) {
            throw new CustomIllegalArgumentException("transportMediumName must not be null.");
        }
        String transportMediumName = transportMediumDTO.getTransportMediumName().toLowerCase();
        String transportMediumSize = "";
        String transportMediumFuel = "";
        switch (transportMediumName) {
            case "pkw":
                if(transportMediumDTO.getTransportMediumSize() == null || transportMediumDTO.getTransportMediumSize().isEmpty()
                        || transportMediumDTO.getTransportMediumSize().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumSize must not be null for case car.");
                }
                transportMediumSize = transportMediumDTO.getTransportMediumSize();
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case car.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                return transportMediumService.findTransportMediumByNameAndSizeAndFuel(transportMediumName,
                        TransportMediumSize.fromName(transportMediumSize),
                        TransportMediumFuel.fromName(transportMediumFuel));

            case "zug":
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case train.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                return transportMediumService.findTransportMediumByNameAndFuel(transportMediumName,
                        TransportMediumFuel.fromName(transportMediumFuel));
            case "buslinie", "busreise":
                return transportMediumService.findTransportMediumByName(transportMediumName);
            default:
                return null;
        }
    }

}
