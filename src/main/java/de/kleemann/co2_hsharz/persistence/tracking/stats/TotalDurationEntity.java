package de.kleemann.co2_hsharz.persistence.tracking.stats;

import jakarta.persistence.Entity;

/**
 * Class "TotalDurationEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
public class TotalDurationEntity extends StatisticEntity {

    private String totalDurationEntityName;
    private long totalDurationEntityAmount;

    public TotalDurationEntity() {}

    public TotalDurationEntity(long id) {
        setStatisticEntityId(id);
    }

    public String getTotalDurationEntityName() {
        return totalDurationEntityName;
    }

    public void setTotalDurationEntityName(String totalDurationEntityName) {
        this.totalDurationEntityName = totalDurationEntityName;
    }

    public long getTotalDurationEntityAmount() {
        return totalDurationEntityAmount;
    }

    public void setTotalDurationEntityAmount(long totalDurationEntityAmount) {
        this.totalDurationEntityAmount = totalDurationEntityAmount;
    }
}
