package de.kleemann.co2_hsharz.core.tracking;

import de.kleemann.co2_hsharz.core.tracking.stats.StatisticImpl;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "VisitorStatsImpl" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class VisitorStatsImpl implements VisitorStats {

    private final VisitorStatsEntity visitorStatsEntity;

    public VisitorStatsImpl(VisitorStatsEntity visitorStatsEntity) {
        this.visitorStatsEntity = visitorStatsEntity;
    }

    @Override
    public long getVisitorStatsId() {
        return visitorStatsEntity.getVisitorStatsId();
    }

    @Override
    public void setVisitorStatsId(long visitorStatsId) {
        visitorStatsEntity.setVisitorStatsId(visitorStatsId);
    }

    @Override
    public List<StatisticEntity> getVisitorStats() {
        return visitorStatsEntity.getVisitorStats();
    }

    @Override
    public void setVisitorStats(List<StatisticImpl> visitorStats) {
        List<StatisticEntity> visitorStatsEntities = new ArrayList<>();
        visitorStats.forEach(statistic -> {
            visitorStatsEntities.add(statistic.getStaticEntity());
        });
        visitorStatsEntity.setVisitorStats(visitorStatsEntities);
    }

    public VisitorStatsEntity getVisitorStatsEntity() {
        return visitorStatsEntity;
    }
}
