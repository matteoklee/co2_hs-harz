package de.kleemann.co2_hsharz.api.transport;

import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<TransportMediumResponseDTO> findAllTransportMediums(HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        try {
            System.err.println("Client IP: " + clientIP + ", whatIsMyIp: " + getPublicIP() + ", Mashine: " + InetAddress.getLocalHost() + ", local?" + request.getLocalAddr());
            System.err.println("AUTHORIZED: " + clientIP.equals(InetAddress.getLocalHost().getHostAddress()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transportMediumService.findAllTransportMediums()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private String getPublicIP() throws IOException {
        URL whatIsMyIP = new URL("http://checkip.amazonaws.com");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(whatIsMyIP.openStream()))) {
            return in.readLine();
        }
    }

    private TransportMediumResponseDTO mapToDTO(TransportMediumImpl transportMedium) {
        TransportMediumResponseDTO dto = new TransportMediumResponseDTO();
        dto.setTransportMediumId(transportMedium.getTransportMediumId());
        dto.setTransportMediumFileName(transportMedium.getTransportMediumFileName());
        dto.setTransportMediumName(transportMedium.getTransportMediumName());
        dto.setTransportMediumSize(transportMedium.getTransportMediumSize());
        dto.setTransportMediumFuel(transportMedium.getTransportMediumFuel());
        dto.setTransportMediumConsumption(transportMedium.getTransportMediumConsumption());
        return dto;
    }



}
