package de.kleemann.co2_hsharz.core.transport.fuel;

import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.persistence.transport.fuel.FuelPersistanceService;
import de.kleemann.co2_hsharz.persistence.transport.fuel.TransportMediumFuel;

/**
 * This Service provides functionality to get all {@link TransportMediumFuel} mapped to {@link FuelImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class FuelService {
	private final FuelPersistanceService fuelPersistanceService;
	
	public FuelService(final FuelPersistanceService fuelPersistanceService) {
		this.fuelPersistanceService = fuelPersistanceService;
	}
	
	/**
	 * Find all available {@link TransportMediumFuel} and mappes them to a list of {@link FuelImpl}
	 * @return {@link List} of {@link FuelImpl}
	 */
	public List<FuelImpl> findAllFuels(){
		return fuelPersistanceService.findAllFuels()
				.stream()
				.map(FuelImpl::new)
				.toList();
				
	}
}
