package de.kleemann.co2_hsharz.persistence.tracking.stats;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "StatisticPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Service
public class StatisticPersistenceService {


    private final StatisticRepository statisticRepository;

    public StatisticPersistenceService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public List<StatisticEntity> findAllStatistics() {
        return new ArrayList<>(statisticRepository.findAll());
    }

    public StatisticEntity findStatisticById(long id) {
        return statisticRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown statistic entity with id: " + id));
    }

    private StatisticEntity saveStatistic(final StatisticEntity statisticEntity) {
        try {
            return statisticRepository.save(statisticEntity);
        } catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("statisticEntity already exists.");
        }
    }

    public StatisticEntity persistStatistic(final StatisticEntity statisticEntity) {
        if(statisticEntity == null) {
            throw new CustomIllegalArgumentException("statisticEntity must not be null.");
        }

        return saveStatistic(statisticEntity);
    }

    public void deleteStatistic(final StatisticEntity statisticEntity) {
        if(statisticEntity == null) {
            throw new CustomIllegalArgumentException("statisticEntity must not be null.");
        }
        statisticRepository.deleteById(statisticEntity.getStatisticEntityId());
    }

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
