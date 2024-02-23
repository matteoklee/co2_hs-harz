package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.tracking.stats.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This Service provides CRUD-Functionality for {@link Statistic}-Objects
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
@Service
public class StatisticService {

	/** {@link StatisticPersistenceService} used for {@link StatisticEntity} crud functionality */
    private final StatisticPersistenceService statisticPersistenceService;
    
    /**
     * Constructs a new {@link StatisticService}
     * @param statisticPersistenceService {@link StatisticPersistenceService} used for {@link StatisticEntity} crud functionality
     */
    public StatisticService(StatisticPersistenceService statisticPersistenceService) {
        this.statisticPersistenceService = statisticPersistenceService;
    }

    /**
     * Retrieves a {@link List} of all {@link StatisticEntity}s mapped as {@link StatisticImpl}
     * @return {@link List} of {@link StatisticImpl} 
     */
    public List<StatisticImpl> findAllStatistics() {
        return statisticPersistenceService.findAllStatistics()
            .stream()
            .map(StatisticImpl::new)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Statistic} by its ID
     * @param id - {@link Long} id
     * @return {@link StatisticImpl} with this id
     */
    public StatisticImpl findStatisticById(long id) {
        try {
            return new StatisticImpl(statisticPersistenceService.findStatisticById(id));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findStatisticById()");
        }
    }

    /**
     * Persists a {@link StatisticImpl}
     * @param statistic {@link StatisticImpl} to persist
     * @return {@link StatisticImpl} persisted object
     */
    public StatisticImpl persistStatistic(final StatisticImpl statistic) {
        if(statistic == null) {
            throw new CustomIllegalArgumentException("statistic must not be null.");
        }
        final StatisticEntity persistedStatisticEntity;
        try {
            persistedStatisticEntity = statisticPersistenceService.persistStatistic(statistic.getStaticEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistStatistic()");
        }

        return new StatisticImpl(persistedStatisticEntity);
    }

    /**
     * Deletes a {@link StatisticImpl}
     * @param statistic {@link StatisticImpl} to delete
     */
    public void deleteStatistic(final StatisticImpl statistic) {
        if(statistic == null) {
            throw new CustomIllegalArgumentException("statistic must not be null.");
        }
        statisticPersistenceService.deleteStatistic(statistic.getStaticEntity());
    }

    /**
     * Creates a {@link StatisticImpl} for a specific type
     * @param type {@link String} statistic entity type
     * @return new {@link StatisticImpl} of this type
     */
    public StatisticImpl createStatisticEntity(String type) {
        if(type == null || type.isBlank()) {
            throw new CustomIllegalArgumentException("statisticType must not be null or empty.");
        }
        switch (type) {
            case "subPageVisit":
                return new SubPageVisit((SubPageVisitEntity) statisticPersistenceService.createStatisticEntity(type));
            case "totalDuration":
                return new TotalDuration((TotalDurationEntity) statisticPersistenceService.createStatisticEntity(type));
            case "buttonClick":
                return new ButtonClick((ButtonClickEntity) statisticPersistenceService.createStatisticEntity(type));
            default:
                return new StatisticImpl(statisticPersistenceService.createStatisticEntity(type));
        }
    }

    /**
     * Creates a {@link StatisticImpl} for a specific type with a specific id
     * @param type {@link String} statistic entity type
     * @param id {@link Long} id of this entity
     * @return new {@link StatisticImpl} of this type
     */
    public StatisticImpl createStatisticEntity(String type, long id) {
        if(type == null || type.isBlank()) {
            throw new CustomIllegalArgumentException("statisticType must not be null or empty.");
        }
        return new StatisticImpl(statisticPersistenceService.createStatisticEntity(type, id));
    }

}
