package de.kleemann.co2_hsharz.core.tracking;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsEntity;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This Service provides CRUD Functionality for {@link VisitorStatsImpl}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
@Service
public class VisitorStatsService {

	/** {@link VisitorStatsPersistenceService} - used for {@link VisitorStatsEntity}-CRUD*/
    private final VisitorStatsPersistenceService visitorStatsPersistenceService;

    /**
     * Constructs a new {@link VisitorStatsService}
     * @param visitorStatsPersistenceService {@link VisitorStatsPersistenceService} used to CRUD {@link VisitorStatsEntity}s
     */
    public VisitorStatsService(VisitorStatsPersistenceService visitorStatsPersistenceService) {
        this.visitorStatsPersistenceService = visitorStatsPersistenceService;
    }

    /**
     * Retrieves a {@link List} of all {@link VisitorStatsEntity}s from Database and maps them to {@link VisitorStatsImpl}
     * @return {@link List} of {@link VisitorStatsImpl}
     */
    public List<VisitorStats> findAllVisitorStats() {
        return visitorStatsPersistenceService.findAllVisitorStats()
            .stream()
            .map(VisitorStatsImpl::new)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link VisitorStatsEntity} by its id
     * @param id {@link Long} id
     * @return {@link VisitorStatsImpl} with this id
     */
    public VisitorStats findVisitorStatsById(long id) {
        try {
            return new VisitorStatsImpl(visitorStatsPersistenceService.findVisitorStatsById(id));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findVisitorStatsById()");
        }
    }

    /**
     * Updates {@link VisitorStats}
     * @param id {@link Long} id of the {@link VisitorStatsEntity} to change
     * @param visitorStats Updates {@link VisitorStats}
     * @return Updated {@link VisitorStats}
     */
    public VisitorStats updateVisitorStats(long id, VisitorStats visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        final VisitorStatsEntity visitorStatsEntity;
        try {
            visitorStatsEntity = visitorStatsPersistenceService.updateVisitorStats(id, ((VisitorStatsImpl) visitorStats).getVisitorStatsEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in updateVisitorStats()");
        }
        return new VisitorStatsImpl(visitorStatsEntity);
    }

    /**
     * Persists {@link VisitorStats}
     * @param visitorStats {@link VisitorStats} to persist
     * @return Persisted {@link VisitorStats}
     */
    public VisitorStats persistVisitorStats(VisitorStats visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        final VisitorStatsEntity visitorStatsEntity;
        try {
            visitorStatsEntity = visitorStatsPersistenceService.persistVisitorStats(((VisitorStatsImpl)visitorStats).getVisitorStatsEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistVisitorStats()");
        }
        return new VisitorStatsImpl(visitorStatsEntity);
    }

    /**
     * Deletes {@link VisitorStats}
     * @param visitorStats {@link VisitorStats} to delete
     */
    public void deleteVisitorStats(VisitorStats visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        visitorStatsPersistenceService.deleteVisitorStats(((VisitorStatsImpl)visitorStats).getVisitorStatsEntity());
    }

    /**
     * Creates a new {@link VisitorStats}-Object
     * @return new {@link VisitorStatsImpl}
     */
    public VisitorStats createVisitorStatsEntity() {
        return new VisitorStatsImpl(visitorStatsPersistenceService.createVisitorStatsEntity());
    }

    /**
     * Creates a new {@link VisitorStats}-Object with a specific id
     * @param id {@link Long} id of the object
     * @return new {@link VisitorStatsImpl}
     */
    public VisitorStats createVisitorStatsEntity(long id) {
        return new VisitorStatsImpl(visitorStatsPersistenceService.createVisitorStatsEntity(id));
    }

}
