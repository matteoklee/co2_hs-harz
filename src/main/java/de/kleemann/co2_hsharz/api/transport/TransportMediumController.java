package de.kleemann.co2_hsharz.api.transport;

import java.util.List;
import java.util.stream.Collectors;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;

/**
 * This API Controller offers the API Endpoint {@code /transport} which can be used to return a list of available {@link TransportMedium}s.
 * <br> See the API Documentation at <a href=https://github.com/matteoklee/co2_hs-harz/wiki/Transport-Endpoint> Github </a> for detailed information
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@RestController
@RequestMapping("/api")
public class TransportMediumController {

    private final TransportMediumService transportMediumService;

    /**
     * Constructor with Auto-Wired required Services
     * 
     * @param transportMediumService - {@link TransportMediumService}
     */
    public TransportMediumController(TransportMediumService transportMediumService) {
        this.transportMediumService = transportMediumService;
    }

    /**
     * Returns a list of all available {@link TransportMedium}s
     * @return {@link ResponseEntity} containing a {@link List} of {@link TransportMediumResponseDTO}
     */
    @GetMapping("/transport")
    public ResponseEntity<List<TransportMediumResponseDTO>> findAllTransportMediums() {
        return new ResponseEntity<>
                (transportMediumService.findAllTransportMediums()
                .stream()
                .map(TransportMediumResponseDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
