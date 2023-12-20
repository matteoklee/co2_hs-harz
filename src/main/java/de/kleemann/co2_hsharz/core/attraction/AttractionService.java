package de.kleemann.co2_hsharz.core.attraction;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.attraction.AttractionEntity;
import de.kleemann.co2_hsharz.persistence.attraction.AttractionPersistenceService;
import lombok.NonNull;

/**
 * This Service provides core layer functionality to create, read, update and delete {@link Attraction}s
 */
@Service
public class AttractionService {

	/**
	 * {@link AttractionPersistenceService}
	 */
    private final AttractionPersistenceService attractionPersistenceService;

    /**
     * Constructs a AttractionService using an {@link AttractionPersistenceService}
     * @param attractionPersistenceService - {@link AttractionPersistenceService}
     */
    public AttractionService(AttractionPersistenceService attractionPersistenceService) {
        this.attractionPersistenceService = attractionPersistenceService;
    }

    /**
     * Returns a possibly empty {@link List} of all {@link Attraction}s in the database
     * @return {@link List} of all {@link Attraction}s
     */
    public List<Attraction> findAllAttractions() {
        return attractionPersistenceService.findAllAttractions()
                .stream()
                .map(Attraction::new)
                .collect(Collectors.toList());
    }

    /**
     * Searches for a single {@link Attraction} using its Id
     * @param attractionId - {@code long} Id or the {@link Attraction}
     * @return A single {@link Attraction}
     * @throws CustomRuntimeException if no attraction is found or an error occurs or CustomIllegalArgumentException if attraction is null
     */
    public Attraction findAttractionById(long attractionId) {
        try {
            return new Attraction(attractionPersistenceService.findAttractionById(attractionId));
        } catch (Exception exception) {
            //throw new RuntimeException(exception);
            throw new CustomRuntimeException("error in findAttractionById()");
        }
    }

    /**
     * Updates a {@link Attraction} identified by {@code attractionId}
     * @param attractionId - {@code long} Id of the Attraction
     * @param attraction - {@link Attraction} New State of the Attraction
     * @return Updated {@link Attraction}
     * @throws CustomRuntimeException if no attraction is found or an error occurs or CustomIllegalArgumentException if attraction is null
     */
    public Attraction updateAttraction(long attractionId, @NonNull Attraction attraction) {
        if (attraction == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        final AttractionEntity updatedAttractionEntity;
        try {
            updatedAttractionEntity = attractionPersistenceService.updateAttraction(attractionId, attraction.getAttractionEntity());
        } catch (Exception exception) {
            //throw new RuntimeException(exception);
            throw new CustomRuntimeException("error in updateAttraction()");
        }
        return new Attraction(updatedAttractionEntity);
    }

    /**
     * Saves an {@link Attraction} to the database
     * 
     * @param attraction - {@link Attraction} to save
     * @return Saved {@link Attraction}
     * @throws CustomRuntimeException if an error occurs or CustomIllegalArgumentException if attraction is null
     */
    public Attraction persistAttraction(@NonNull final Attraction attraction) {
        if (attraction == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        final AttractionEntity persistedAttractionEntity;
        try {
            persistedAttractionEntity = attractionPersistenceService.persistAttraction(attraction.getAttractionEntity());
        } catch (Exception exception) {
            //throw new RuntimeException(exception);
            throw new CustomRuntimeException("error in persistAttraction()");
        }
        return new Attraction(persistedAttractionEntity);
    }

    /**
     * Deletes an {@link Attraction}
     * @param attraction - {@link Attraction} to delete
     * @throws CustomIllegalArgumentException if attraction is null
     */
    public void deleteAttraction(final Attraction attraction) {
        if (attraction == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        attractionPersistenceService.deleteAttraction(attraction.getAttractionEntity());
    }

    /**
     * Creates a new Attraction
     * @return created Attraction
     */
    public Attraction createAttraction() {
        return new Attraction(attractionPersistenceService.createAttractionEntity());
    }

    /**
     * Creates a new Attraction with a set id
     * @param id - {@code long} Id
     * @return created Attraction
     */
    public Attraction createAttraction(long id) {
        return new Attraction(attractionPersistenceService.createAttractionEntity(id));
    }


}
