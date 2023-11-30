package de.kleemann.co2_hsharz.persistence.transport.size;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

/**
 * This Service provides...
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class SizePersistanceService {
	
	/**
	 * Finds all available Size Types and returns them as a list. 
	 * Should not be empty
	 * @return {@link List} of {@link TransportMediumSize}
	 */
	public List<TransportMediumSize> findAllSizes() {
		return Arrays.asList(TransportMediumSize.values());
	}
	
	/**
	 * Finds a Size from given name
	 * @param name - Name or ID of the {@link TransportMediumSize}
	 * @return {@link TransportMediumSize} or null, if name does not correlate to a fuel
	 */
	@Nullable
	public TransportMediumSize findFuel(String name) {
		return TransportMediumSize.fromName(name);
	}
}
