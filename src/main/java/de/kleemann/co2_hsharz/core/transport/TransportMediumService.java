package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

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

    public List<TransportMediumImpl> findAllTransportMediums() {
        return transportMediumPersistenceService.findAllTransportMediums()
                .stream()
                .map(TransportMediumImpl::new)
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


    public TransportMediumImpl findTransportMediumByCustomInput(TransportMediumDTO transportMediumDTO) {
        if(transportMediumDTO.getTransportMediumName() == null || transportMediumDTO.getTransportMediumName().isBlank()
                || transportMediumDTO.getTransportMediumName().isEmpty()) {
            throw new CustomIllegalArgumentException("transportMediumName must not be null.");
        }
        //String transportMediumName = transportMediumDTO.getTransportMediumName().toLowerCase();
        TransportMediumName transportMediumName = TransportMediumName.fromName(transportMediumDTO.getTransportMediumName());
        if(transportMediumName == null) {
            throw new CustomIllegalArgumentException("transportMediumName could not be found.");
        }
        String transportMediumSize = "";
        String transportMediumFuel = "";
        switch (transportMediumName) {
            case CAR:
                if(transportMediumDTO.getTransportMediumSize() == null || transportMediumDTO.getTransportMediumSize().isEmpty()
                        || transportMediumDTO.getTransportMediumSize().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumSize must not be null for case car.");
                }
                transportMediumSize = transportMediumDTO.getTransportMediumSize();
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case car.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumSize.fromName(transportMediumSize) == null) {
                    throw new CustomIllegalArgumentException("transportMediumSize does not exists.");
                }
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndSizeAndFuel(transportMediumName,
                        TransportMediumSize.fromName(transportMediumSize),
                        TransportMediumFuel.fromName(transportMediumFuel));

            case TRAIN:
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case train.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndFuel(transportMediumName,
                        TransportMediumFuel.fromName(transportMediumFuel));
            case BUS_TOUR, BIKE:
                return findTransportMediumByName(transportMediumName);
            case BUS_PUBLIC:
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case bus_public.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndFuel(transportMediumName,
                        TransportMediumFuel.fromName(transportMediumFuel));
            case FOOT:
                TransportMediumImpl transportMedium = createTransportMedium();
                transportMedium.setTransportMediumName(TransportMediumName.FOOT);
                transportMedium.setTransportMediumConsumption(0.0);
                return transportMedium;
            default:
                throw new CustomIllegalArgumentException("findTransportMediumByCustomInput could not be found.");
        }
    }


}
