package de.kleemann.co2_hsharz.core.tracking;

import de.kleemann.co2_hsharz.core.tracking.stats.StatisticImpl;
import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;

import java.util.List;

/**
 * Class "VisitorStats" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public interface VisitorStats {

    public long getVisitorStatsId();

    public void setVisitorStatsId(long visitorStatsId);

    public List<StatisticEntity> getVisitorStats();

    public void setVisitorStats(List<StatisticImpl> visitorStats);

}
