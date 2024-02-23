package de.kleemann.co2_hsharz.persistence.tracking.stats;

import de.kleemann.co2_hsharz.core.tracking.stats.Statistic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This entity represents a abstract {@link Statistic}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public abstract class StatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long statisticEntityId;
    protected String statisticEntityType;
}
