package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.persistence.attraction.AttractionEntity;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "TransportMediumPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class TransportMediumPersistenceService {


    private final TransportMediumRepository transportMediumRepository;

    public TransportMediumPersistenceService(TransportMediumRepository transportMediumRepository) {
        this.transportMediumRepository = transportMediumRepository;
    }

    public List<TransportMediumEntity> findAllTransportMediums() {
        return new ArrayList<>(transportMediumRepository.findAll());
    }

    public TransportMediumEntity findTransportMediumById(long transportMediumId) {
        return transportMediumRepository.findById(transportMediumId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown transport medium with id: " + transportMediumId));
    }

    public TransportMediumEntity findTransportMediumByName(String transportMediumName) {
        return transportMediumRepository.findFirstByTransportMediumNameLike(transportMediumName);
    }

    public TransportMediumEntity findTransportMediumByNameAndSizeAndFuel(String transportMediumName,
                                                                         TransportMediumSize transportMediumSize,
                                                                         TransportMediumFuel transportMediumFuel) {
        return transportMediumRepository
                .findFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel(transportMediumName,
                        transportMediumSize, transportMediumFuel);
    }

    public boolean existsByTransportMediumFileName(String transportMediumFileName) {
        return transportMediumRepository .existsByTransportMediumFileName(transportMediumFileName);
    }


    public TransportMediumEntity updateTransportMedium(long transportMediumId, TransportMediumEntity transportMediumEntity) {
        if (transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null.");
        }
        TransportMediumEntity updateTransportMediumEntity = findTransportMediumById(transportMediumId);
        updateTransportMediumEntity.setTransportMediumId(transportMediumId);
        updateTransportMediumEntity.setTransportMediumFileName(transportMediumEntity.getTransportMediumFileName());
        updateTransportMediumEntity.setTransportMediumName(transportMediumEntity.getTransportMediumName());
        updateTransportMediumEntity.setTransportMediumSize(transportMediumEntity.getTransportMediumSize());
        updateTransportMediumEntity.setTransportMediumFuel(transportMediumEntity.getTransportMediumFuel());
        updateTransportMediumEntity.setTransportMediumConsumption(transportMediumEntity.getTransportMediumConsumption());

        return saveTransportMedium(updateTransportMediumEntity);
    }

    private TransportMediumEntity saveTransportMedium(final TransportMediumEntity transportMediumEntity) {
        try {
            return transportMediumRepository.save(transportMediumEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("transportMediumEntity already exists.");
        }
    }

    public TransportMediumEntity persistTransportMedium(final TransportMediumEntity transportMediumEntity) {
        if (transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null.");
        }
        /*
        if (attractionEntity.getAttractionId() != null) {
            throw new IllegalArgumentException("new attraction must not contain an id.");
        }
        */
        return saveTransportMedium(transportMediumEntity);
    }

    public void deleteTransportMedium(final TransportMediumEntity transportMediumEntity) {
        if (transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null!");
        }
        transportMediumRepository.deleteById(transportMediumEntity.getTransportMediumId());
    }

    public TransportMediumEntity createTransportMediumEntity() {
        return new TransportMediumEntity();
    }

    public TransportMediumEntity createTransportMediumEntity(long id) {
        return new TransportMediumEntity(id);
    }


}
