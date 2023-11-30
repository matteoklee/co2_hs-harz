package de.kleemann.co2_hsharz.core.transport.size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This Service provides functionality to get all {@link TransportMediumSize} mapped to {@link TransportMediumSizeImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class TransportMediumSizeService {

	private final TransportMediumService transportMediumService;

	public TransportMediumSizeService(final TransportMediumService transportMediumService) {
		this.transportMediumService = transportMediumService;
	}
	
	/**
	 * Find all available {@link TransportMediumSize} and mappes them to a list of {@link TransportMediumSizeImpl}
	 * @return {@link List} of {@link TransportMediumSizeImpl}
	 */
	public List<TransportMediumSizeImpl> findAllTransportMediumSizes(){
		return Arrays.asList(TransportMediumSize.values()).stream().map(TransportMediumSizeImpl::new).collect(Collectors.toList());
	}

	public List<TransportMediumSizeImpl> findAllAvailableSizesForTransportMedium(String transportMediumId) {
		TransportMediumName transportMediumName = TransportMediumName.fromName(transportMediumId);
		if(transportMediumName == null) {
			throw new CustomIllegalArgumentException("unknown transportMedium for id: " + transportMediumId);
		}
		List<TransportMediumImpl> transportMediums = transportMediumService.findAllTransportMediums();
		List<TransportMediumSize> transportMediumSizes = new ArrayList<>();

		transportMediums.removeIf(transportMedium -> !transportMedium.getTransportMediumName().equals(transportMediumName));
		transportMediums.stream().forEach(transportMedium -> {
			if(!transportMediumSizes.contains(transportMedium.getTransportMediumSize()) && transportMedium.getTransportMediumSize() != null) {
				transportMediumSizes.add(transportMedium.getTransportMediumSize());
			}
		});
		return transportMediumSizes.stream().map(TransportMediumSizeImpl::new).collect(Collectors.toList());
	}

	@Nullable
	public TransportMediumSizeImpl findTransportMediumSizeByName(String transportMediumName) {
		return new TransportMediumSizeImpl(TransportMediumSize.fromName(transportMediumName));
	}

}
