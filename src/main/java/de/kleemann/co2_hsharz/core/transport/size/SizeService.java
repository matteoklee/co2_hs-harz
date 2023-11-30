package de.kleemann.co2_hsharz.core.transport.size;

import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.persistence.transport.size.SizePersistanceService;
import de.kleemann.co2_hsharz.persistence.transport.size.TransportMediumSize;

/**
 * This Service provides functionality to get all {@link TransportMediumSize} mapped to {@link SizeImpl}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@Service
public class SizeService {
private final SizePersistanceService sizePersistanceService;
	
	public SizeService(final SizePersistanceService sizePersistanceService) {
		this.sizePersistanceService = sizePersistanceService;
	}
	
	/**
	 * Find all available {@link TransportMediumSize} and mappes them to a list of {@link SizeImpl}
	 * @return {@link List} of {@link SizeImpl}
	 */
	public List<SizeImpl> findAllSizes(){
		return sizePersistanceService.findAllSizes()
				.stream()
				.map(SizeImpl::new)
				.toList();
				
	}
}
