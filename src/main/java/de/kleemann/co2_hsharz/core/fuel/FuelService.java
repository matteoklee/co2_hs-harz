package de.kleemann.co2_hsharz.core.fuel;

import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.persistence.fuel.FuelPersistanceService;

/**
 * This Service provides...
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
	
	public List<FuelImpl> findAllFuels(){
		return fuelPersistanceService.findAllFuels()
				.stream()
				.map(FuelImpl::new)
				.toList();
				
	}
}
