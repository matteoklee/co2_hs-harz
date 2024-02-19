package de.kleemann.co2_hsharz.core.tracking;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsEntity;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "VisitorStatsService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
@Service
public class VisitorStatsService {

    private final VisitorStatsPersistenceService visitorStatsPersistenceService;

    public VisitorStatsService(VisitorStatsPersistenceService visitorStatsPersistenceService) {
        this.visitorStatsPersistenceService = visitorStatsPersistenceService;
    }

    public List<VisitorStatsImpl> findAllVisitorStats() {
        return visitorStatsPersistenceService.findAllVisitorStats()
            .stream()
            .map(VisitorStatsImpl::new)
            .collect(Collectors.toList());
    }

    public VisitorStatsImpl findVisitorStatsById(long id) {
        try {
            return new VisitorStatsImpl(visitorStatsPersistenceService.findVisitorStatsById(id));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findVisitorStatsById()");
        }
    }

    public VisitorStatsImpl updateVisitorStats(long id, VisitorStatsImpl visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        final VisitorStatsEntity visitorStatsEntity;
        try {
            visitorStatsEntity = visitorStatsPersistenceService.updateVisitorStats(id, visitorStats.getVisitorStatsEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in updateVisitorStats()");
        }
        return new VisitorStatsImpl(visitorStatsEntity);
    }

    public VisitorStatsImpl persistVisitorStats(VisitorStatsImpl visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        final VisitorStatsEntity visitorStatsEntity;
        try {
            visitorStatsEntity = visitorStatsPersistenceService.persistVisitorStats(visitorStats.getVisitorStatsEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistVisitorStats()");
        }
        return new VisitorStatsImpl(visitorStatsEntity);
    }

    public void deleteVisitorStats(VisitorStatsImpl visitorStats) {
        if(visitorStats == null) {
            throw new CustomIllegalArgumentException("visitorStats must not be null.");
        }
        visitorStatsPersistenceService.deleteVisitorStats(visitorStats.getVisitorStatsEntity());
    }

    public VisitorStatsImpl createVisitorStatsEntity() {
        return new VisitorStatsImpl(visitorStatsPersistenceService.createVisitorStatsEntity());
    }

    public VisitorStatsImpl createVisitorStatsEntity(long id) {
        return new VisitorStatsImpl(visitorStatsPersistenceService.createVisitorStatsEntity(id));
    }

}
