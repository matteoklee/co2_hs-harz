package de.kleemann.co2_hsharz.core.emissions;

import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
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

    public double calculateEmission(TransportMediumImpl transportMedium, double distance) {
        double transportMediumConsumption = transportMedium.getTransportMediumConsumption(); //1 kg/km
        double emission = transportMediumConsumption * ((double) (distance/1000));
        return emission;
    }

}
