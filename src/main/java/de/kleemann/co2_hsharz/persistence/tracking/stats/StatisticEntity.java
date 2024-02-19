package de.kleemann.co2_hsharz.persistence.tracking.stats;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class "StatisticDataEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
public abstract class StatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long statisticEntityId;
    protected String statisticEntityType;

    //public abstract Class<? extends StatisticEntity> getType();
    public StatisticEntity() {}

    public StatisticEntity(long statisticEntityId, String statisticEntityType) {
        this.statisticEntityId = statisticEntityId;
        this.statisticEntityType = statisticEntityType;
    }

    public long getStatisticEntityId() {
        return statisticEntityId;
    }

    public void setStatisticEntityId(long statisticEntityId) {
        this.statisticEntityId = statisticEntityId;
    }

    public String getStatisticEntityType() {
        return statisticEntityType;
    }

    public void setStatisticEntityType(String statisticEntityType) {
        this.statisticEntityType = statisticEntityType;
    }
}
