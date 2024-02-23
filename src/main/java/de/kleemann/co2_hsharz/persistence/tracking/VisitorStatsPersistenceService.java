package de.kleemann.co2_hsharz.persistence.tracking;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "VisitorStatsPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Service
public class VisitorStatsPersistenceService {

    private final VisitorStatsRepository visitorStatsRepository;

    public VisitorStatsPersistenceService(VisitorStatsRepository visitorStatsRepository) {
        this.visitorStatsRepository = visitorStatsRepository;
    }

    public List<VisitorStatsEntity> findAllVisitorStats() {
        return new ArrayList<>(visitorStatsRepository.findAll());
    }

    public VisitorStatsEntity findVisitorStatsById(long id) {
        return visitorStatsRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown visitorStats entity with id: " + id));
    }

    public VisitorStatsEntity updateVisitorStats(long visitorStatsId, VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }
        VisitorStatsEntity updateVisitorStatsEntity = findVisitorStatsById(visitorStatsId);
        updateVisitorStatsEntity.setVisitorStatsId(visitorStatsId);
        updateVisitorStatsEntity.setVisitorStats(visitorStatsEntity.getVisitorStats());

        return saveVisitorStats(updateVisitorStatsEntity);
    }

    private VisitorStatsEntity saveVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        try {
            return visitorStatsRepository.save(visitorStatsEntity);
        } catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("visitorStatsEntity already exists.");
        }
    }

    public VisitorStatsEntity persistVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }

        return saveVisitorStats(visitorStatsEntity);
    }

    public void deleteVisitorStats(final VisitorStatsEntity visitorStatsEntity) {
        if(visitorStatsEntity == null) {
            throw new CustomIllegalArgumentException("visitorStatsEntity must not be null.");
        }
        visitorStatsRepository.deleteById(visitorStatsEntity.getVisitorStatsId());
    }

    public VisitorStatsEntity createVisitorStatsEntity() {
        return new VisitorStatsEntity();
    }

    public VisitorStatsEntity createVisitorStatsEntity(long id) {
        return new VisitorStatsEntity(id);
    }

}
