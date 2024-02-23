package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This Service provides crud functionality for the {@link TransportMediumEntity}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class TransportMediumPersistenceService {

	/** {@link TransportMediumRepository} used for database queries */
    private final TransportMediumRepository transportMediumRepository;

    /**
     * Constructs a new {@link TransportMediumPersistenceService}
     * @param transportMediumRepository {@link TransportMediumRepository} used for database queries
     */
    public TransportMediumPersistenceService(TransportMediumRepository transportMediumRepository) {
        this.transportMediumRepository = transportMediumRepository;
    }

    /**
     * Retrieves a {@link List} of all {@link TransportMediumEntity}s
     * @return {@link List} of {@link TransportMediumEntity}s
     */
    public List<TransportMediumEntity> findAllTransportMediums() {
        return new ArrayList<>(transportMediumRepository.findAll());
    }

    /**
     * Retrieves a {@link TransportMediumEntity} by its id
     * @param transportMediumId {@link Long} id
     * @return {@link TransportMediumEntity} with this id
     */
    public TransportMediumEntity findTransportMediumById(long transportMediumId) {
        return transportMediumRepository.findById(transportMediumId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown transport medium with id: " + transportMediumId));
    }

    /**
     * Retrieves the first {@link TransportMediumEntity} with a {@link TransportMediumName}
     * @param transportMediumName {@link TransportMediumName}
     * @return first {@link TransportMediumEntity} with this name
     */
    public TransportMediumEntity findTransportMediumByName(TransportMediumName transportMediumName) {
        return transportMediumRepository.getFirstByTransportMediumNameOrderByTransportMediumVersionDesc(transportMediumName);
    }

    /**
     * Retrieves the first {@link TransportMediumEntity} with this {@link TransportMediumName} and {@link TransportMediumFuel}
     * @param transportMediumName {@link TransportMediumName}
     * @param transportMediumFuel {@link TransportMediumFuel}
     * @return First {@link TransportMediumEntity} with this attributes
     */
    public TransportMediumEntity findTransportMediumByNameAndFuel(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel) {
        return transportMediumRepository
                .getFirstByTransportMediumNameAndTransportMediumFuelOrderByTransportMediumVersionDesc(transportMediumName, transportMediumFuel);
    }

    /**
     * Retrieves the first {@link TransportMediumEntity} with this {@link TransportMediumName}, {@link TransportMediumSize} and {@link TransportMediumFuel}
     * @param transportMediumName {@link TransportMediumName}
     * @param transportMediumSize {@link TransportMediumSize}
     * @param transportMediumFuel {@link TransportMediumFuel}
     * @return First {@link TransportMediumEntity} with this attributes
     */
    public TransportMediumEntity findTransportMediumByNameAndSizeAndFuel(TransportMediumName transportMediumName,
                                                                         TransportMediumSize transportMediumSize,
                                                                         TransportMediumFuel transportMediumFuel) {
        return transportMediumRepository
                .getFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuelOrderByTransportMediumVersionDesc(
                        transportMediumName, transportMediumSize, transportMediumFuel);
    }

    /**
     * Checks the existence of a {@link TransportMediumEntity} with this filename
     * @param transportMediumFileName {@link String} filename
     * @return <code>true</code>, if it exists
     */
    public boolean existsByTransportMediumFileName(String transportMediumFileName) {
        return transportMediumRepository.existsByTransportMediumFileName(transportMediumFileName);
    }

    /**
     * Retrieves the first {@link TransportMediumEntity} with this filename
     * @param transportMediumFileName - {@link String} filename
     * @return {@link TransportMediumEntity} with this filename
     */
    public TransportMediumEntity findTransportMediumByFileName(String transportMediumFileName) {
        return transportMediumRepository
                .getFirstByTransportMediumFileNameOrderByTransportMediumVersionDesc(transportMediumFileName);
    }

    /**
     * Updates a {@link TransportMediumEntity}
     * @param transportMediumId {@link Long} Id of the {@link TransportMediumEntity} to update
     * @param transportMediumEntity updated {@link TransportMediumEntity} 
     * @return Updated {@link TransportMediumEntity}
     */
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

    /**
     * Saves a {@link TransportMediumEntity} to database
     * @param transportMediumEntity {@link TransportMediumEntity} to save
     * @return Saved {@link TransportMediumEntity}
     */
    private TransportMediumEntity saveTransportMedium(final TransportMediumEntity transportMediumEntity) {
        try {
            return transportMediumRepository.save(transportMediumEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("transportMediumEntity already exists.");
        }
    }

    /**
     * Persists a {@link TransportMediumEntity} to database
     * @param transportMediumEntity {@link TransportMediumEntity} to persist
     * @return Persisted {@link TransportMediumEntity}
     */
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

    /**
     * Deletes a {@link TransportMediumEntity}
     * @param transportMediumEntity {@link TransportMediumEntity} to delete
     */
    public void deleteTransportMedium(final TransportMediumEntity transportMediumEntity) {
        if (transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null!");
        }
        transportMediumRepository.deleteById(transportMediumEntity.getTransportMediumId());
    }

    /**
     * Creates a new {@link TransportMediumEntity}
     * @return new {@link TransportMediumEntity}
     */
    public TransportMediumEntity createTransportMediumEntity() {
        return new TransportMediumEntity();
    }

    /**
     * Creates a new {@link TransportMediumEntity} with a specific id
     * @param id {@link Long} id
     * @return new {@link TransportMediumEntity} with this id
     */
    public TransportMediumEntity createTransportMediumEntity(long id) {
        return new TransportMediumEntity(id);
    }


}
