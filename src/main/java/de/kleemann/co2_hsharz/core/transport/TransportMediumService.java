package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public TransportMedium findTransportMediumById(long id) {
        return null;
    }

    public TransportMedium findTransportMediumByName(String transportMediumName) {
        return new TransportMedium(transportMediumPersistenceService.findAllTransportMediums()
                .stream()
                .filter(transportMediumEntity -> transportMediumEntity.getTransportName().equals(transportMediumName)).findFirst().get());

    }

    public List<TransportMedium> findAllTransportMediums() {
        return null;
    }

    public TransportMedium createTransportMedium() {
        return null;
    }

    public TransportMedium updateTransportMedium() {
        return null;
    }

    public void deleteTransportMedium() {

    }


}
