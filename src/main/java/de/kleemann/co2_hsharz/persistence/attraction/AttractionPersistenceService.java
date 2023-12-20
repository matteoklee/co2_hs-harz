package de.kleemann.co2_hsharz.persistence.attraction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.attraction.Attraction;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import lombok.NonNull;

/**
 * This Service provides persistence layer functionality to create, read, update and delete {@link AttractionEntity}s
 */
@Service
public class AttractionPersistenceService {

    private final AttractionRepository attractionRepository;

    /**
     * Constructs a {@link AttractionPersistenceService} using an {@link AttractionRepository}
     * @param attractionRepository - {@link AttractionRepository}
     */
    public AttractionPersistenceService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    /**
     * Returns a {@link List} of all {@link AttractionEntity}s in the database
     * 
     * @return {@link List} of all {@link AttractionEntity}s
     */
    public List<AttractionEntity> findAllAttractions() {
        return new ArrayList<>(attractionRepository.findAll());
    }

    /**
     * Searches an {@link AttractionEntity} by Id
     * 
     * @param attractionId - {@code long} Id of the {@link AttractionEntity}
     * @return {@link AttractionEntity} with this id
     * @throws CustomEntityNotFoundException if no entity is found
     */
    public AttractionEntity findAttractionById(long attractionId) {
        return attractionRepository.findById(attractionId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown attraction with id: " + attractionId));
    }

    /**
     * Updates an {@link AttractionEntity} found by Id
     * 
     * @param attractionId - {@code long} Id of the {@link AttractionEntity}
     * @param attractionEntity - Changed {@link AttractionEntity}
     * @return Updated {@link AttractionEntity}
     * @throws CustomIllegalArgumentException if {@code attractionEntity} is null
     */
    public AttractionEntity updateAttraction(long attractionId, @NonNull AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        AttractionEntity updateAttractionEntity = findAttractionById(attractionId);
        updateAttractionEntity.setAttractionId(attractionId);
        updateAttractionEntity.setAttractionName(attractionEntity.getAttractionName());
        updateAttractionEntity.setAttractionLocation(attractionEntity.getAttractionLocation());
        return saveAttraction(updateAttractionEntity);
    }

    /**
     * Saves an {@link AttractionEntity}
     * 
     * @param attractionEntity - {@link AttractionEntity} to save
     * @return Saved {@link AttractionEntity}
     * @throws CustomEntityExistsException if the Attraction already exists
     */
    private AttractionEntity saveAttraction(@NonNull final AttractionEntity attractionEntity) {
        try {
            return attractionRepository.save(attractionEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("attraction already exists.");
        }
    }

    /**
     * Persists an {@link AttractionEntity}
     * 
     * @param attractionEntity - {@link AttractionEntity} to persist
     * @return Persisted {@link AttractionEntity}
     * @throws CustomIllegalArgumentException if {@code attractionEntity} is null
     */
    public AttractionEntity persistAttraction(@NonNull final AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        /*
        if (attractionEntity.getAttractionId() != null) {
            throw new IllegalArgumentException("new attraction must not contain an id.");
        }
        */
        return saveAttraction(attractionEntity);
    }

    /**
     * Deletes an {@link AttractionEntity}
     * 
     * @param attractionEntity - {@link AttractionEntity} to delete
     * @throws CustomIllegalArgumentException if {@code attractionEntity} is null
     */
    public void deleteAttraction(@NonNull final AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null!");
        }
        attractionRepository.deleteById(attractionEntity.getAttractionId());
    }

    /**
     * Creates a new {@link AttractionEntity}
     * @return new {@link AttractionEntity}
     */
    public AttractionEntity createAttractionEntity() {
        return new AttractionEntity();
    }

    /**
     * Creates a new {@link Attraction} with id
     * @param id - {@code long} id for this entity
     * @return new {@link AttractionEntity} with this id
     */
    public AttractionEntity createAttractionEntity(long id) {
        return new AttractionEntity(id);
    }

}
