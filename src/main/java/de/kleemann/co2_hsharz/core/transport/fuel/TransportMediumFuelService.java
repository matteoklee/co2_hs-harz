package de.kleemann.co2_hsharz.core.transport.fuel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import lombok.NonNull;

/**
 * This Service provides functionality to get all {@link TransportMediumFuel} mapped to {@link TransportMediumFuelImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class TransportMediumFuelService {

	/**
	 * {@link TransportMediumService}
	 */
	private final TransportMediumService transportMediumService;

	/**
	 * Constructs a new {@link TransportMediumFuelService}
	 * @param transportMediumService - {@link TransportMediumService}
	 */
	public TransportMediumFuelService(final TransportMediumService transportMediumService) {
		this.transportMediumService = transportMediumService;
	}
	
	/**
	 * Find all available {@link TransportMediumFuel} and maps them to a list of {@link TransportMediumFuelImpl}
	 * @return {@link List} of {@link TransportMediumFuelImpl}
	 */
	public List<TransportMediumFuelImpl> findAllTransportMediumFuels(){
		return Arrays.asList(TransportMediumFuel.values()).stream().map(TransportMediumFuelImpl::new).collect(Collectors.toList());
	}

	/**
	 * Finds all available {@link TransportMediumFuel} for a specific {@link TransportMedium} and maps them to a list of {@link TransportMediumFuelImpl}
	 * @param transportMediumId - {@link String} Name or Id of a {@link TransportMedium}
	 * @return {@link List} of {@link TransportMediumFuelImpl}
	 * @throws CustomIllegalArgumentException if the {@link TransportMediumName} found for transportMediumId is null
	 */
	public List<TransportMediumFuelImpl> findAllAvailableFuelsForTransportMedium(@NonNull String transportMediumId) {
		TransportMediumName transportMediumName = TransportMediumName.fromName(transportMediumId);
		if(transportMediumName == null) {
			throw new CustomIllegalArgumentException("unknown transportMedium for id: " + transportMediumId);
		}
		List<TransportMediumImpl> transportMediums = transportMediumService.findAllTransportMediums();
		List<TransportMediumFuel> transportMediumFuels = new ArrayList<>();

		transportMediums.removeIf(transportMedium -> !transportMedium.getTransportMediumName().equals(transportMediumName));
		transportMediums.stream().forEach(transportMedium -> {
			if(!transportMediumFuels.contains(transportMedium.getTransportMediumFuel()) && transportMedium.getTransportMediumFuel() != null) {
				transportMediumFuels.add(transportMedium.getTransportMediumFuel());
			}
		});
		return transportMediumFuels.stream().map(TransportMediumFuelImpl::new).collect(Collectors.toList());
	}

	/**
	 * Attempts to find a TransportMediumFuel by its name
	 * @param transportMediumFuelName - {@link String} Name of the {@link TransportMediumFuel}
	 * @return {@link TransportMediumFuelImpl} with this name, or null, if none is found
	 */
	@Nullable
	public TransportMediumFuelImpl findTransportMediumFuelByName(String transportMediumFuelName) {
		return new TransportMediumFuelImpl(TransportMediumFuel.fromName(transportMediumFuelName));
	}

}
