package de.kleemann.co2_hsharz.api.transport;

import java.util.List;
import java.util.stream.Collectors;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.TransportMediumService;

/**
 * Class "TransportMediumController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@RestController
@RequestMapping("/api")
public class TransportMediumController {

    private final TransportMediumService transportMediumService;

    public TransportMediumController(TransportMediumService transportMediumService) {
        this.transportMediumService = transportMediumService;
    }

    @GetMapping("/transport")
    public ResponseEntity<List<TransportMediumResponseDTO>> findAllTransportMediums() {
        return new ResponseEntity<>
                (transportMediumService.findAllTransportMediums()
                .stream()
                .map(TransportMediumResponseDTO::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
