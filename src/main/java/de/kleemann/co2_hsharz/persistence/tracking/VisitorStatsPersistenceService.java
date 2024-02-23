package de.kleemann.co2_hsharz.persistence.tracking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;

/**
 * This service provides crud functionality for the {@link VisitorStatsEntity}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Service
public class VisitorStatsPersistenceService {

	/** {@link VisitorStatsRepository} for database queries */
    private final VisitorStatsRepository visitorStatsRepository;

    /**
     * Constructs a new {@link VisitorStatsPersistenceService}
     * @param visitorStatsRepository {@link VisitorStatsRepository} for database queries
     */
    public VisitorStatsPersistenceService(VisitorStatsRepository visitorStatsRepository) {
        this.visitorStatsRepository = visitorStatsRepository;
    }

    /**
     * Retrieves a {@link List} of all {@link VisitorStatsEntity}s saved in database
     * @return {@link List} of {@link VisitorStatsEntity}
     */
    public List<VisitorStatsEntity> findAllVisitorStats() {
        return new ArrayList<>(visitorStatsRepository.findAll());
    }

    /**
     * Retrieves a {@link VisitorStatsEntity} by its id
     * @param id {@link Long} id
     * @return {@link VisitorStatsEntity} with this id
     */
    public VisitorStatsEntity findVisitorStatsById(long id) {
        return visitorStatsRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown visitorStats entity with id: " + id));
    }

    /**
     * Updates a {@link VisitorStatsEntity}
     * @param visitorStatsId {@link Long} id of the {@link VisitorStatsEntity} to update
     * @param visitorStatsEntity updated {@link VisitorStatsEntity}
     * @return Updated {@link VisitorStatsEntity}
     */
    public VisitorStatsEntity updateVisitorStats(long visitorStatsId, VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }
        VisitorStatsEntity updateVisitorStatsEntity = findVisitorStatsById(visitorStatsId);
        updateVisitorStatsEntity.setVisitorStatsId(visitorStatsId);
        updateVisitorStatsEntity.setVisitorStats(visitorStatsEntity.getVisitorStats());

        return saveVisitorStats(updateVisitorStatsEntity);
    }

    /**
     * Saves a {@link VisitorStatsEntity} to database
     * @param visitorStatsEntity {@link VisitorStatsEntity} to save
     * @return Saved {@link VisitorStatsEntity}
     */
    private VisitorStatsEntity saveVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        try {
            return visitorStatsRepository.save(visitorStatsEntity);
        } catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("visitorStatsEntity already exists.");
        }
    }

    /**
     * Persists a {@link VisitorStatsEntity}
     * @param visitorStatsEntity {@link VisitorStatsEntity} to persist
     * @return Persistd {@link VisitorStatsEntity}
     */
    public VisitorStatsEntity persistVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }

        return saveVisitorStats(visitorStatsEntity);
    }

    /**
     * Deletes a {@link VisitorStatsEntity}
     * @param visitorStatsEntity {@link VisitorStatsEntity} to delete
     */
    public void deleteVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }
        visitorStatsRepository.deleteById(visitorStatsEntity.getVisitorStatsId());
    }

    /**
     * Creates a new {@link VisitorStatsEntity}
     * @return new {@link VisitorStatsEntity}
     */
    public VisitorStatsEntity createVisitorStatsEntity() {
        return new VisitorStatsEntity();
    }

    /**
     * Creates a new {@link VisitorStatsEntity} with a specific id
     * @param id {@link Long} id
     * @return new {@link VisitorStatsEntity}
     */
    public VisitorStatsEntity createVisitorStatsEntity(long id) {
        return new VisitorStatsEntity(id);
    }

}
