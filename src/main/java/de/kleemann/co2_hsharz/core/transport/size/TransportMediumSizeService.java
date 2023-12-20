package de.kleemann.co2_hsharz.core.transport.size;

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
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This Service provides functionality to get all, or specific {@link TransportMediumSize} mapped to {@link TransportMediumSizeImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class TransportMediumSizeService {

	/**
	 * {@link TransportMediumService}
	 */
	private final TransportMediumService transportMediumService;

	/**
	 * Constructs a new {@link TransportMediumSizeService}
	 * @param transportMediumService - {@link TransportMediumService}
	 */
	public TransportMediumSizeService(final TransportMediumService transportMediumService) {
		this.transportMediumService = transportMediumService;
	}
	
	/**
	 * Find all available {@link TransportMediumSize} and maps them to a list of {@link TransportMediumSizeImpl}
	 * @return {@link List} of {@link TransportMediumSizeImpl}
	 */
	public List<TransportMediumSizeImpl> findAllTransportMediumSizes(){
		return Arrays.asList(TransportMediumSize.values()).stream().map(TransportMediumSizeImpl::new).collect(Collectors.toList());
	}

	/**
	 * Finds all available {@link TransportMediumSize} for a specific {@link TransportMedium} and maps them to a list of {@link TransportMediumSizeImpl}
	 * @param transportMediumId - {@link String} Name or Id of a {@link TransportMedium}
	 * @return {@link List} of {@link TransportMediumSizeImpl}
	 * @throws CustomIllegalArgumentException if the {@link TransportMediumName} found for transportMediumId is null
	 */
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

	/**
	 * Attempts to find a TransportMediumSize by its name
	 * @param transportMediumSizeName - {@link String} Name of the {@link TransportMediumSize}
	 * @return {@link TransportMediumSizeImpl} with this name, or null, if none is found
	 */
	@Nullable
	public TransportMediumSizeImpl findTransportMediumSizeByName(String transportMediumName) {
		return new TransportMediumSizeImpl(TransportMediumSize.fromName(transportMediumName));
	}

}
