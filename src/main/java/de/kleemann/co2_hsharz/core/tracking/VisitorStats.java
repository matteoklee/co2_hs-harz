package de.kleemann.co2_hsharz.core.tracking;

import de.kleemann.co2_hsharz.core.tracking.stats.Statistic;
import de.kleemann.co2_hsharz.core.tracking.stats.StatisticImpl;
import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;

import java.util.List;

/**
 * This Interface represents a bundle of statistics, 
 * that are captured by the frontend and used to analyse the software
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public interface VisitorStats {

	/**
	 * Returns the ID of this object
	 * @return {@link Long} id
	 */
    public long getVisitorStatsId();

    /**
     * Replaces the ID of the object
     * @param visitorStatsId {@link Long} new ID
     */
    public void setVisitorStatsId(long visitorStatsId);

    /**
     * Returns the {@link List} of {@link StatisticEntity}s bundled in this {@link VisitorStats}-Object
     * @return {@link List} of {@link StatisticEntity}s
     */
    public List<StatisticEntity> getVisitorStats();

    /**
     * Replaces the {@link List} of {@link Statistic}s
     * @param visitorStats new {@link List} of {@link StatisticImpl}s
     */
    public void setVisitorStats(List<StatisticImpl> visitorStats);

}
