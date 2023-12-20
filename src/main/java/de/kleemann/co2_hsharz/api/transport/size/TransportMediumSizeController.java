package de.kleemann.co2_hsharz.api.transport.size;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.size.TransportMediumSizeImpl;
import de.kleemann.co2_hsharz.core.transport.size.TransportMediumSizeService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This API Controller provides functions to retrieve all size types, as well as available size types for specific transportMediumNames
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@RestController
@RequestMapping("/api/transport")
public class TransportMediumSizeController {

	private final TransportMediumSizeService transportMediumSizeService;

	/**
	 * Constructor with Auto-Wired required Services
	 * 
	 * @param transportMediumSizeService - {@link TransportMediumSizeService}
	 */
	public TransportMediumSizeController(final TransportMediumSizeService transportMediumSizeService) {
		this.transportMediumSizeService = transportMediumSizeService;
	}
	 
	/**
	 * Returns a list of all TransportMediumSizes available
	 * @return {@link ResponseEntity} containing a {@link List} of {@link TransportMediumSizeImpl}
	 */
	@GetMapping("/size")
	public ResponseEntity<List<TransportMediumSizeImpl>> findAllTransportMediumSizes() {
		return new ResponseEntity<>(transportMediumSizeService.findAllTransportMediumSizes(), HttpStatus.OK);
	}
	
	/**
	  * Finds a {@link List} of {@link TransportMediumSize}s available for a {@link TransportMedium}
	  * @param transportMediumId - {@link String} Id or Name of a {@link TransportMedium}
	  * @return {@link ResponseEntity} containing a {@link List} of {@link TransportMediumSizeImpl}
	  */
	@GetMapping("/size/{id}")
	public ResponseEntity<List<TransportMediumSizeImpl>> findAllAvailableSizesForTransportMedium(@PathVariable(value = "id") String transportMediumId) {
		return new ResponseEntity<>
				(transportMediumSizeService.findAllAvailableSizesForTransportMedium(transportMediumId), HttpStatus.OK);
	}
}
