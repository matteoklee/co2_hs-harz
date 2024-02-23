package de.kleemann.co2_hsharz.persistence.tracking.stats;

import de.kleemann.co2_hsharz.core.tracking.stats.TotalDuration;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Entity represents a {@link TotalDuration}-Statistic.
 * 
 * @see StatisticEntity
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TotalDurationEntity extends StatisticEntity {

    private String totalDurationEntityName;
    private long totalDurationEntityAmount;

    public TotalDurationEntity(long id) {
        setStatisticEntityId(id);
    }
}
