package de.kleemann.co2_hsharz.api.transport.size;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.size.TransportMediumSizeImpl;
import de.kleemann.co2_hsharz.core.transport.size.TransportMediumSizeService;

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

	public TransportMediumSizeController(final TransportMediumSizeService transportMediumSizeService) {
		this.transportMediumSizeService = transportMediumSizeService;
	}
	 
	@GetMapping("/size")
	public ResponseEntity<List<TransportMediumSizeImpl>> findAllTransportMediumSizes() {
		return new ResponseEntity<>(transportMediumSizeService.findAllTransportMediumSizes(), HttpStatus.OK);
	}
	@GetMapping("/size/{id}")
	public ResponseEntity<List<TransportMediumSizeImpl>> findAllAvailableSizesForTransportMedium(@PathVariable(value = "id") String transportMediumId) {
		return new ResponseEntity<>
				(transportMediumSizeService.findAllAvailableSizesForTransportMedium(transportMediumId), HttpStatus.OK);
	}
}
