package de.kleemann.co2_hsharz.persistence.transport.fuel;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

/**
 * This Service provides functionality to find fuel types
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class FuelPersistanceService {

	/**
	 * Finds all available Fuel Types and returns them as a list.
	 * Should not be empty
	 * @return {@link List} of {@link TransportMediumFuel}
	 */
	public List<TransportMediumFuel> findAllFuels() {
		return Arrays.asList(TransportMediumFuel.values());
	}
	
	/**
	 * Finds a Fuel Type from given name
	 * @param name - Name or ID of the {@link TransportMediumFuel}
	 * @return {@link TransportMediumFuel} or null, if name does not correlate to a fuel
	 */
	@Nullable
	public TransportMediumFuel findFuel(String name) {
		return TransportMediumFuel.fromName(name);
	}
}
