package de.kleemann.co2_hsharz.api.transport.size;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.api.transport.TransportMediumResponseDTO;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.core.transport.size.SizeImpl;
import de.kleemann.co2_hsharz.core.transport.size.SizeService;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.size.TransportMediumSize;

/**
 * This API Controller provides functions to retrieve all size types, as well as available size types for specific transportMediumNames
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
@RestController
@RequestMapping("/api/transport")
public class SizeController {
	private final SizeService sizeService;
	private final TransportMediumService transportMediumService;
	 
	public SizeController(final SizeService sizeService, final TransportMediumService transportMediumService) {
		this.sizeService = sizeService;
		this.transportMediumService = transportMediumService;
	}
	 
	@GetMapping("/size")
	 public ResponseEntity<List<SizeImpl>> findAllSizes() {
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(sizeService.findAllSizes());
	 }
	
	@GetMapping("/size/{id}")
	 public ResponseEntity<Object> findSizesAvailableForTransportMedium(@PathVariable String id) {
		 TransportMediumName name = TransportMediumName.fromName(id);
		 
		 if(name == null)
			 return ResponseEntity
					 .status(HttpStatus.BAD_REQUEST)
					 .body("Transportmedium mit angegebener ID nicht gefunden");
		 
		 List<TransportMediumResponseDTO> mediums = transportMediumService.findAllTransportMediums();
		 List<TransportMediumSize> sizes = new LinkedList<TransportMediumSize>();
		 
		 mediums.removeIf(entry -> !entry.getTransportMediumName().equals(name));
		 mediums.stream().forEach(entry -> {
			if(!sizes.contains(entry.getTransportMediumSize()) && entry.getTransportMediumSize() != null)
				sizes.add(
					entry.getTransportMediumSize()
				);
		 });
		 
		 return ResponseEntity
				 .status(HttpStatus.OK)
				 .body(sizes.stream().map(SizeImpl::new).toList());
	 }
}
