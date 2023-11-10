package de.kleemann.co2_hsharz.core.emissions;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import org.springframework.stereotype.Service;

/**
 * Class "EmissionsCalculationService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class EmissionsCalculationService {

    public EmissionsCalculationService() {

    }

    public double calculateEmission(TransportMedium transportMedium, double distance) {
        double emission = transportMedium.getConsumption() * distance;
        return emission;
    }

}
