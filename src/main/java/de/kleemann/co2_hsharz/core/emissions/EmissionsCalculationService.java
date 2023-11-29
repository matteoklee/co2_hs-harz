package de.kleemann.co2_hsharz.core.emissions;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
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
        if(transportMedium == null) {
            throw new CustomIllegalArgumentException("transportMedium must not be null.");
        }
        if(distance <= 0.0) {
            throw new CustomIllegalArgumentException("distance between start and end must be greater than 0.");
        }
        double transportMediumConsumption = transportMedium.getTransportMediumConsumption(); //1 kg/km
        if(transportMediumConsumption < 0) {
            transportMediumConsumption = 0;
        }
        double emission = transportMediumConsumption * ((double) (distance/1000));
        return emission;
    }

}
