package de.kleemann.co2_hsharz.core.emissions;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
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

    //https://www.helmholtz.de/newsroom/artikel/wie-viel-co2-steckt-in-einem-liter-benzin
    //https://spritrechner.biz/co2-rechner-fuer-autos.html#:~:text=Benzin%3A%20Verbrauch%20pro%20100%20Kilometer,145%2C8%20g%20CO2%2Fkm
    //https://www.cng-mobility.ch/so-viel-co2-sparen-sie-mit-cng/
    //pro 1 Liter
    private final double PETROL_EMISSION_OUTPUT = 2.37;
    private final double DIESEL_EMISSION_OUTPUT = 2.65;

    public double calculateCustomEmission(TransportMediumDTO transportMediumDTO, double distance) {
        double fuelConsumption = transportMediumDTO.getTransportMediumFuelConsumption();
        if(fuelConsumption <= 0) {
            throw new CustomIllegalArgumentException("fuel consumption must be greater than 0.");
        }
        if(distance <= 0.0) {
            throw new CustomIllegalArgumentException("distance between start and end must be greater than 0.");
        }
        if(TransportMediumName.fromName(transportMediumDTO.getTransportMediumName()) != TransportMediumName.CAR) {
            throw new CustomIllegalArgumentException("custom emission can only be calculated for cars.");
        }

        TransportMediumFuel transportMediumFuel = TransportMediumFuel.fromName(transportMediumDTO.getTransportMediumFuel());
        if(transportMediumFuel == null) {
            throw new CustomIllegalArgumentException("transportMediumFuel must not be null.");
        }

        double totalFuelConsumption = (fuelConsumption / 100) * (distance/1000);

        double emission = 0;
        switch (transportMediumFuel) {
            case PETROL -> emission = totalFuelConsumption * PETROL_EMISSION_OUTPUT;
            case DIESEL -> emission = totalFuelConsumption * DIESEL_EMISSION_OUTPUT;
            default -> throw new CustomIllegalArgumentException("transportMediumFuel could not be found.");
        }

        return emission;

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
