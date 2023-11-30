package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.api.transport.TransportMediumResponseDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import de.kleemann.co2_hsharz.persistence.transport.fuel.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.size.TransportMediumSize;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "TransportMediumService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class TransportMediumService {

    private final TransportMediumPersistenceService transportMediumPersistenceService;

    public TransportMediumService(TransportMediumPersistenceService transportMediumPersistenceService) {
        this.transportMediumPersistenceService = transportMediumPersistenceService;
    }

    public TransportMediumImpl findTransportMediumById(long id) {
        return null;
    }

    //return TransportMedium interface? --> requires mapper?
    public TransportMediumImpl findTransportMediumByName(TransportMediumName transportMediumName) {
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByName(transportMediumName));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByName could not be found.");
        }
    }

    public TransportMediumImpl findTransportMediumByNameAndFuel(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel) {
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByNameAndFuel(transportMediumName, transportMediumFuel));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByNameAndFuel could not be found.");
        }
    }

    public TransportMediumImpl findTransportMediumByNameAndSizeAndFuel(TransportMediumName transportMediumName,
                                                                       TransportMediumSize transportMediumSize,
                                                                       TransportMediumFuel transportMediumFuel) {
        System.err.println("DEBUG: " + transportMediumName + ", Size: " + transportMediumSize + ", Fuel: " + transportMediumFuel);
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByNameAndSizeAndFuel(transportMediumName, transportMediumSize, transportMediumFuel));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByNameAndSizeAndFuel could not be found.");
        }
    }

    public List<TransportMediumResponseDTO> findAllTransportMediums() {
        return transportMediumPersistenceService.findAllTransportMediums()
                .stream()
                .map(TransportMediumResponseDTO::new)
                .collect(Collectors.toList());
    }

    public TransportMediumImpl createTransportMedium() {
        return new TransportMediumImpl(transportMediumPersistenceService.createTransportMediumEntity());
    }

    public TransportMediumImpl createTransportMedium(long transportMediumId) {
        return new TransportMediumImpl(transportMediumPersistenceService.createTransportMediumEntity(transportMediumId));
    }

    public TransportMediumImpl updateTransportMedium() {
        return null;
    }

    public TransportMediumImpl persistTransportMedium() {
        return null;
    }

    public void deleteTransportMedium(final TransportMediumImpl transportMedium) {
        if(transportMedium == null) {
            throw new CustomIllegalArgumentException("transportMedium must not be null.");
        }
        transportMediumPersistenceService.deleteTransportMedium(transportMedium.getTransportMediumEntity());
    }


}
