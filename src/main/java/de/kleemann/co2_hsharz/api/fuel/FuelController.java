package de.kleemann.co2_hsharz.api.fuel;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.api.transport.TransportMediumResponseDTO;
import de.kleemann.co2_hsharz.core.fuel.FuelImpl;
import de.kleemann.co2_hsharz.core.fuel.FuelService;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.fuel.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumName;

/**
 * This API Controller provides functions to retrieve all fuel types, as well as available fuel types for specific transportMediumNames
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@RestController
@RequestMapping("/api")
public class FuelController {
	 private final FuelService fuelService;
	 private final TransportMediumService transportMediumService;

	 public FuelController(final FuelService fuelService, final TransportMediumService transportMediumService) {
		 this.fuelService = fuelService;
		 this.transportMediumService = transportMediumService;
	 }
	
	 @GetMapping("/fuel")
	 public ResponseEntity<List<FuelImpl>> findAllFuels() {
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(fuelService.findAllFuels());
	 }
	
	 @GetMapping("/fuel/{id}")
	 public ResponseEntity<Object> findFuelsAvailableForTransportMedium(@PathVariable String id) {
		 TransportMediumName name = TransportMediumName.fromName(id);
		 
		 if(name == null)
			 return ResponseEntity
					 .status(HttpStatus.BAD_REQUEST)
					 .body("Transportmedium mit angegebener ID nicht gefunden");
		 
		 List<TransportMediumResponseDTO> mediums = transportMediumService.findAllTransportMediums();
		 List<TransportMediumFuel> fuels = new LinkedList<TransportMediumFuel>();
		 
		 mediums.removeIf(entry -> !entry.getTransportMediumName().equals(name));
		 mediums.stream().forEach(entry -> {
			if(!fuels.contains(entry.getTransportMediumFuel()))
				fuels.add(
					entry.getTransportMediumFuel()
				);
		 });
		 
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(fuels.stream().map(FuelImpl::new).toList());
	 }
}
