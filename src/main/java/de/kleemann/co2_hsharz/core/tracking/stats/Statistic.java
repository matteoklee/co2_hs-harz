package de.kleemann.co2_hsharz.core.tracking.stats;

/**
 * Class "Statistik" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public interface Statistic {

    /**
     * Gibt die ID des StatisticEntity zurück
     * @return long id
     */
    public long getStatisticEntityId();

    /**
     * Setzt die ID des StatisticEntity
     * @param statisticEntityId - long neue Id
     */
    public void setStatisticEntityId(long statisticEntityId);

    /**
     * Gibt den Typ des StatisticEntity zurück
     * @return {@link String} type
     */
    public String getStatisticEntityType();

    /**
     * Setzt den Typ des StatisticEntity
     * @param statisticEntityType - {@link String} neuer Typ
     */
    public void setStatisticEntityType(String statisticEntityType);
}
