package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.tracking.stats.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "StatisticService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
@Service
public class StatisticService {

    private final StatisticPersistenceService statisticPersistenceService;

    public StatisticService(StatisticPersistenceService statisticPersistenceService) {
        this.statisticPersistenceService = statisticPersistenceService;
    }

    public List<StatisticImpl> findAllStatistics() {
        return statisticPersistenceService.findAllStatistics()
            .stream()
            .map(StatisticImpl::new)
            .collect(Collectors.toList());
    }

    public StatisticImpl findStatisticById(long id) {
        try {
            return new StatisticImpl(statisticPersistenceService.findStatisticById(id));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findStatisticById()");
        }
    }

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

    public void deleteStatistic(final StatisticImpl statistic) {
        if(statistic == null) {
            throw new CustomIllegalArgumentException("statistic must not be null.");
        }
        statisticPersistenceService.deleteStatistic(statistic.getStaticEntity());
    }

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

    public StatisticImpl createStatisticEntity(String type, long id) {
        if(type == null || type.isBlank()) {
            throw new CustomIllegalArgumentException("statisticType must not be null or empty.");
        }
        return new StatisticImpl(statisticPersistenceService.createStatisticEntity(type, id));
    }

}
