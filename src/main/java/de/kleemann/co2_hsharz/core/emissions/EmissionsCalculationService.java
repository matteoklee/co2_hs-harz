package de.kleemann.co2_hsharz.core.emissions;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import lombok.NonNull;

import org.springframework.stereotype.Service;

/**
 * This Service provides core layer functionality to calculate the co2 emitted on a route using a specific vehicle
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class EmissionsCalculationService {

	/**
	 * Default Constructor
	 */
    public EmissionsCalculationService() {

    }

    /**
     * CO2 Emission per Liter
     *
     * Sources:
     * <li> <a href="https://www.helmholtz.de/newsroom/artikel/wie-viel-co2-steckt-in-einem-liter-benzin">Helmholtz.de</a>
     * <li> <a href="https://spritrechner.biz/co2-rechner-fuer-autos.html#:~:text=Benzin%3A%20Verbrauch%20pro%20100%20Kilometer,145%2C8%20g%20CO2%2Fkm">Spritrechner.biz</a>
     * <li> <a https://www.cng-mobility.ch/so-viel-co2-sparen-sie-mit-cng/">cng-mobility.ch</a>
     */
    private final double PETROL_EMISSION_OUTPUT = 2.37;
    /**
     * CO2 Emission per Liter
     *
     * Sources:
     * <li> <a href="https://www.helmholtz.de/newsroom/artikel/wie-viel-co2-steckt-in-einem-liter-benzin">Helmholtz.de</a>
     * <li> <a href="https://spritrechner.biz/co2-rechner-fuer-autos.html#:~:text=Benzin%3A%20Verbrauch%20pro%20100%20Kilometer,145%2C8%20g%20CO2%2Fkm">Spritrechner.biz</a>
     * <li> <a https://www.cng-mobility.ch/so-viel-co2-sparen-sie-mit-cng/">cng-mobility.ch</a>
     */
    private final double DIESEL_EMISSION_OUTPUT = 2.65;

    /**
     * Calculates the co2 emission for a vehicle with custom fuel consumption on a given distance
     * @param transportMediumDTO - {@link TransportMediumDTO} vehicle used on the route
     * @param distance - {@code double} length of the route
     * @return {@code double} Estimated CO2 Equivalents emitted
     * @throws CustomIllegalArgumentException 
     * 		<li> if {@link TransportMediumDTO#getTransportMediumFuelConsumption()} is equal or less then 0.0
     * 		<li> if {@code distance} is equal or less then 0.0	
     * 		<li> if {@link TransportMediumDTO#getTransportMediumName()} is not {@link TransportMediumName#CAR}
     * 		<li> if {@link TransportMediumDTO#getTransportMediumFuel()} is null
     * 		<li> if {@link TransportMediumDTO#getTransportMediumFuel()} is not {@link TransportMediumFuel#DIESEL} or {@link TransportMediumFuel#PETROL}
     */
    public double calculateCustomEmission(@NonNull TransportMediumDTO transportMediumDTO, double distance) {
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

    /**
     * Calculates the co2 emission for a vehicle on a given distance
     * @param transportMedium - {@link TransportMediumImpl} vehicle used on the route
     * @param distance - {@code double} length of the route
     * @return {@code double} Estimated CO2 Equivalents emitted in kilograms
     * @throws CustomIllegalArgumentException
     * 		<li> if {@code transportMedium} is null
     * 		<li> if {@code distance} is equal or less then 0
     */
    public double calculateEmission(@NonNull TransportMediumImpl transportMedium, double distance) {
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
