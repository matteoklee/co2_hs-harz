package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.TotalDurationEntity;
import lombok.NonNull;

/**
 * This Class is a specialization of the {@link StatisticImpl} and tracks visit duration in the frontend
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class TotalDuration extends StatisticImpl {

	/** {@link ButtonClickEntity} used to create this Object */
    private TotalDurationEntity totalDurationEntity;

    /**
     * Constructs a new {@link TotalDuration}-Object
     * @param totalDurationEntity {@link TotalDurationEntity} used to create this object
     */
    public TotalDuration(@NonNull TotalDurationEntity totalDurationEntity){
        super(totalDurationEntity);
        this.totalDurationEntity = totalDurationEntity;
    }

    /**
     * Returns the name of this statistic
     * @return {@link String} name
     */
    public String getTotalDurationName() {
        return this.totalDurationEntity.getTotalDurationEntityName();
    }

    /**
     * Sets the name of this statistic
     * @param totalDurationName {@link String} new name
     */
    public void setTotalDurationName(String totalDurationName) {
        this.totalDurationEntity.setTotalDurationEntityName(totalDurationName);
    }

    /**
     * Returns the total duration of the user visit on this page
     * @return {@link Long} total duration
     */
    public long getTotalDurationAmount() {
        return this.totalDurationEntity.getTotalDurationEntityAmount();
    }

    /**
     * Sets the total duration of the user visit
     * @param totalDurationAmount {@link Long} new total duration
     */
    public void setTotalDurationAmount(long totalDurationAmount) {
        this.totalDurationEntity.setTotalDurationEntityAmount(totalDurationAmount);
    }
}
