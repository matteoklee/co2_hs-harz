package de.kleemann.co2_hsharz.api.transport.dto;

import de.kleemann.co2_hsharz.api.transport.TransportMediumController;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class is a Data Transfer Object (DTO) for the {@link TransportMediumController#findAllTransportMediums()} Response
 * and represents a {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 29.11.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportMediumResponseDTO {

    private long transportMediumId;
    private String transportMediumFileName;
    private TransportMediumName transportMediumName;
    private TransportMediumSize transportMediumSize;
    private TransportMediumFuel transportMediumFuel;
    private double transportMediumConsumption;
    private double transportMediumVersion;
    
    /**
     * Constructs this DTO from a {@link TransportMediumImpl}
     * @param transportMedium {@link TransportMediumImpl} to create this object from 
     */
    public TransportMediumResponseDTO(TransportMediumImpl transportMedium) {
    	this(transportMedium.getTransportMediumId(), transportMedium.getTransportMediumFileName(),
                transportMedium.getTransportMediumName(), transportMedium.getTransportMediumSize(),
                transportMedium.getTransportMediumFuel(), transportMedium.getTransportMediumConsumption(), transportMedium.getTransportMediumVersion());
    }
}
