package de.kleemann.co2_hsharz.persistence.tracking.stats;

import de.kleemann.co2_hsharz.core.tracking.stats.ButtonClick;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Entity represents a {@link ButtonClick}-Statistic.
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
public class ButtonClickEntity extends StatisticEntity {

    private String buttonClickEntityName;
    private int buttonClickEntityAmount;

    public ButtonClickEntity(long id) {
        setStatisticEntityId(id);
    }
}
