package de.kleemann.co2_hsharz.api.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import jakarta.servlet.http.HttpServletRequest;

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
    public ResponseEntity<Object> findAllTransportMediums(HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        try {
            System.err.println("Client IP: " + clientIP + ", whatIsMyIp: " + getPublicIP() + ", local?: " + request.getLocalAddr());
        } catch (IOException e) {
        	return ResponseEntity
        			.status(HttpStatus.FORBIDDEN)
        			.body(e.getLocalizedMessage());
        }
        
        try {
        	List<TransportMediumResponseDTO> list = transportMediumService.findAllTransportMediums();
        	return ResponseEntity
        			.status(HttpStatus.OK)
        			.body(list);
        }
        catch(Exception e) {
        	return ResponseEntity
        			.status(HttpStatus.INTERNAL_SERVER_ERROR)
        			.body(e.getLocalizedMessage());
        }
        
    }

    private String getPublicIP() throws IOException {
        URL whatIsMyIP = new URL("http://checkip.amazonaws.com");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(whatIsMyIP.openStream()))) {
            return in.readLine();
        }
    }

}
