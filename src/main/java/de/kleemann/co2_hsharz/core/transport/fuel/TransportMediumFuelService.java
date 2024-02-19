package de.kleemann.co2_hsharz.core.transport.fuel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.core.transport.size.TransportMediumSizeImpl;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;

/**
 * This Service provides functionality to get all {@link TransportMediumFuel} mapped to {@link TransportMediumFuelImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class TransportMediumFuelService {

	private final TransportMediumService transportMediumService;

	public TransportMediumFuelService(final TransportMediumService transportMediumService) {
		this.transportMediumService = transportMediumService;
	}
	
	/**
	 * Find all available {@link TransportMediumFuel} and mappes them to a list of {@link TransportMediumFuelImpl}
	 * @return {@link List} of {@link TransportMediumFuelImpl}
	 */
	public List<TransportMediumFuelImpl> findAllTransportMediumFuels(){
		return Arrays.asList(TransportMediumFuel.values()).stream().map(TransportMediumFuelImpl::new).collect(Collectors.toList());
	}

	public List<TransportMediumFuelImpl> findAllAvailableFuelsForTransportMedium(String transportMediumId) {
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

	@Nullable
	public TransportMediumFuelImpl findTransportMediumFuelByName(String transportMediumName) {
		return new TransportMediumFuelImpl(TransportMediumFuel.fromName(transportMediumName));
	}

}
