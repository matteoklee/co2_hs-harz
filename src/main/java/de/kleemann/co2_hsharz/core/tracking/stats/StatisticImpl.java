package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;
import lombok.NonNull;

/**
 * This is the implementation class of {@link Statistic}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class StatisticImpl implements Statistic {

	/** {@link StatisticEntity} used to construct this Object */
    private StatisticEntity staticEntity;

    /**
     * Constructs a new {@link StatisticImpl}
     * @param statisticEntity {@link StatisticEntity} used to construct this object
     */
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

    /**
     * Returns the {@link StatisticEntity} used to construct this object
     * @return {@link StatisticEntity} used to construct this object
     */
    public StatisticEntity getStaticEntity() {
        return staticEntity;
    }
}
