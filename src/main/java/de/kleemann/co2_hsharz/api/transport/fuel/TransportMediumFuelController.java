package de.kleemann.co2_hsharz.api.transport.fuel;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.core.transport.fuel.TransportMediumFuelImpl;
import de.kleemann.co2_hsharz.core.transport.fuel.TransportMediumFuelService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import lombok.NonNull;

/**
 * This API Controller provides functions to retrieve all fuel types, as well as available fuel types for specific transportMediumNames
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@RestController
@RequestMapping("/api/transport")
public class TransportMediumFuelController {
	 private final TransportMediumFuelService transportMediumFuelService;
	 private final TransportMediumService transportMediumService;

	 /**
	  * Constructor with Auto-Wired required Services
	  * 
	  * @param transportMediumFuelService - {@link TransportMediumFuelService}
	  * @param transportMediumService - {@link TransportMediumService}
	  */
	 public TransportMediumFuelController(final TransportMediumFuelService transportMediumFuelService, final TransportMediumService transportMediumService) {
		 this.transportMediumFuelService = transportMediumFuelService;
		 this.transportMediumService = transportMediumService;
	 }
	
	 /**
	  * Returns a list of all TransportMediumFuels available
	  * @return {@link ResponseEntity} containing a {@link List} of {@link TransportMediumFuelImpl}
	  */
	 @GetMapping("/fuel")
	 public ResponseEntity<List<TransportMediumFuelImpl>> findAllTransportMediumFuels() {
		 return new ResponseEntity<>(transportMediumFuelService.findAllTransportMediumFuels(), HttpStatus.OK);
	 }

	 /**
	  * Finds a {@link List} of {@link TransportMediumFuel}s available for a {@link TransportMedium}
	  * @param transportMediumId - {@link String} Id or Name of a {@link TransportMedium}
	  * @return {@link ResponseEntity} containing a {@link List} of {@link TransportMediumFuelImpl}
	  */
	 @GetMapping("/fuel/{id}")
	 public ResponseEntity<List<TransportMediumFuelImpl>> findAllAvailableFuelsForTransportMedium(@NonNull @PathVariable(value = "id") String transportMediumId) {
		 return new ResponseEntity<>
		 		(transportMediumFuelService.findAllAvailableFuelsForTransportMedium(transportMediumId), HttpStatus.OK);
	 }
}
