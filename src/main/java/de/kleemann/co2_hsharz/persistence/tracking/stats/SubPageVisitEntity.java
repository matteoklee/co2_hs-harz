package de.kleemann.co2_hsharz.persistence.tracking.stats;

import de.kleemann.co2_hsharz.core.tracking.stats.SubPageVisit;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  This Entity represents a {@link SubPageVisit}-Statistic.
 * 
 * @see StatisticEntity
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class SubPageVisitEntity extends StatisticEntity {

    private String subPageVisitEntityName;
    private int subPageVisitEntityTotalVisits;

    public SubPageVisitEntity(long id) {
        setStatisticEntityId(id);
    }
}
