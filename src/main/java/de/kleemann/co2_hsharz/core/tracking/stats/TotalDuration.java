package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.TotalDurationEntity;
import lombok.NonNull;

/**
 * Class "TotalDuration" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class TotalDuration extends StatisticImpl {

    private TotalDurationEntity totalDurationEntity;

    public TotalDuration(@NonNull TotalDurationEntity totalDurationEntity){
        super(totalDurationEntity);
        this.totalDurationEntity = totalDurationEntity;
    }

    public String getTotalDurationName() {
        return this.totalDurationEntity.getTotalDurationEntityName();
    }

    public void setTotalDurationName(String totalDurationName) {
        this.totalDurationEntity.setTotalDurationEntityName(totalDurationName);
    }

    public long getTotalDurationAmount() {
        return this.totalDurationEntity.getTotalDurationEntityAmount();
    }

    public void setTotalDurationAmount(long totalDurationAmount) {
        this.totalDurationEntity.setTotalDurationEntityAmount(totalDurationAmount);
    }
}
