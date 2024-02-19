package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;
import lombok.NonNull;

/**
 * Class "StatisticImpl" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class StatisticImpl implements Statistic {

    private StatisticEntity staticEntity;

    public StatisticImpl(@NonNull StatisticEntity statisticEntity){
        this.staticEntity = statisticEntity;
    }

    /** {@inheritDoc} */
    @Override
    public long getStatisticEntityId() {
        return this.staticEntity.getStatisticEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public String getStatisticEntityType() {
        return this.staticEntity.getStatisticEntityType();
    }

    /** {@inheritDoc} */
    @Override
    public void setStatisticEntityType(String type) {
        this.staticEntity.setStatisticEntityType(type);
    }

    /** {@inheritDoc} */
    @Override
    public void setStatisticEntityId(long id) {
        this.staticEntity.setStatisticEntityId(id);
    }

    public StatisticEntity getStaticEntity() {
        return staticEntity;
    }
}
