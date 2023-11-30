package de.kleemann.co2_hsharz.persistence.fuel;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * This Service provides...
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class FuelPersistanceService {

	public List<TransportMediumFuel> findAllFuels() {
		return Arrays.asList(TransportMediumFuel.values());
	}
	
	public TransportMediumFuel findFuel(String name) {
		return TransportMediumFuel.fromName(name);
	}

}
