package de.kleemann.co2_hsharz.persistence.tracking.stats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;

/**
 * This Service provides crud functionality for the {@link StatisticEntity}	
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Service
public class StatisticPersistenceService {

	/** {@link StatisticRepository} */
    private final StatisticRepository statisticRepository;

    /**
     * Constructs a new {@link StatisticPersistenceService}
     * @param statisticRepository {@link StatisticRepository} used for database queries
     */
    public StatisticPersistenceService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    /**
     * Retrieves a {@link List} of all {@link StatisticEntity}s
     * @return {@link List} of {@link StatisticEntity}s
     */
    public List<StatisticEntity> findAllStatistics() {
        return new ArrayList<>(statisticRepository.findAll());
    }

    /**
     * Retrieves a {@link StatisticEntity} by its id
     * @param id {@link Long} id
     * @return {@link StatisticEntity} with this id
     */
    public StatisticEntity findStatisticById(long id) {
        return statisticRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown statistic entity with id: " + id));
    }

    /**
     * Saves a {@link StatisticEntity}
     * @param statisticEntity {@link StatisticEntity} to save
     * @return saved {@link StatisticEntity}
     */
    private StatisticEntity saveStatistic(final StatisticEntity statisticEntity) {
        try {
            return statisticRepository.save(statisticEntity);
        } catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("statisticEntity already exists.");
        }
    }

    /**
     * Persists a {@link StatisticEntity}
     * @param statisticEntity {@link StatisticEntity} to persist
     * @return persisted {@link StatisticEntity}
     */
    public StatisticEntity persistStatistic(final StatisticEntity statisticEntity) {
        if(statisticEntity == null) {
            throw new CustomIllegalArgumentException("statisticEntity must not be null.");
        }

        return saveStatistic(statisticEntity);
    }

    /**
     * Deletes a {@link StatisticEntity}
     * @param statisticEntity {@link StatisticEntity} to delete
     */
    public void deleteStatistic(final StatisticEntity statisticEntity) {
        if(statisticEntity == null) {
            throw new CustomIllegalArgumentException("statisticEntity must not be null.");
        }
        statisticRepository.deleteById(statisticEntity.getStatisticEntityId());
    }

    /**
     * Creates a new {@link StatisticEntity} of this type
     * @param type {@link String} type
     * @return new {@link StatisticEntity} of this type
     */
    public StatisticEntity createStatisticEntity(String type) {
        //TODO: is String null?
        switch (type) {
            case "subPageVisit":
                return new SubPageVisitEntity();
            case "buttonClick":
                return new ButtonClickEntity();
            case "totalDuration":
                return new TotalDurationEntity();
            default:
                throw new CustomIllegalArgumentException("statistic type does not exists.");
        }
    }

    /**
     * Creates a new {@link StatisticEntity} of this type and with this id
     * @param type {@link String} type
     * @param id {@link Long} id
     * @return new {@link StatisticEntity} of this type
     */
    public StatisticEntity createStatisticEntity(String type, long id) {
        //TODO: check String and id
        switch (type) {
            case "subPageVisit":
                return new SubPageVisitEntity(id);
            case "buttonClick":
                return new ButtonClickEntity(id);
            case "totalDuration":
                return new TotalDurationEntity(id);
            default:
                throw new CustomIllegalArgumentException("statistic type does not exists.");
        }
    }
}
