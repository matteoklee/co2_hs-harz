package de.kleemann.co2_hsharz.core.tracking;

import java.util.ArrayList;
import java.util.List;

import de.kleemann.co2_hsharz.core.tracking.stats.StatisticImpl;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;

/**
 * This Class is the implementation of the {@link VisitorStats}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class VisitorStatsImpl implements VisitorStats {

	/** {@link VisitorStatsEntity} - used to create this object */
    private final VisitorStatsEntity visitorStatsEntity;

    /**
     * Constructs a new {@link VisitorStatsImpl} of a {@link VisitorStatsEntity}
     * @param visitorStatsEntity {@link VisitorStatsEntity} used to create this object
     */
    public VisitorStatsImpl(VisitorStatsEntity visitorStatsEntity) {
        this.visitorStatsEntity = visitorStatsEntity;
    }

    /** {@inheritDoc} */
    @Override
    public long getVisitorStatsId() {
        return visitorStatsEntity.getVisitorStatsId();
    }

    /** {@inheritDoc} */
    @Override
    public void setVisitorStatsId(long visitorStatsId) {
        visitorStatsEntity.setVisitorStatsId(visitorStatsId);
    }

    /** {@inheritDoc} */
    @Override
    public List<StatisticEntity> getVisitorStats() {
        return visitorStatsEntity.getVisitorStats();
    }

    /** {@inheritDoc} */
    @Override
    public void setVisitorStats(List<StatisticImpl> visitorStats) {
        List<StatisticEntity> visitorStatsEntities = new ArrayList<>();
        visitorStats.forEach(statistic -> {
            visitorStatsEntities.add(statistic.getStaticEntity());
        });
        visitorStatsEntity.setVisitorStats(visitorStatsEntities);
    }

    /**
     * Returns the {@link VisitorStatsEntity} used to create this object
     * @return {@link VisitorStatsEntity} used to create this object
     */
    public VisitorStatsEntity getVisitorStatsEntity() {
        return visitorStatsEntity;
    }
}
