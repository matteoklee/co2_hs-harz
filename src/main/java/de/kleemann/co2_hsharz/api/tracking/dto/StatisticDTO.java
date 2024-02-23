package de.kleemann.co2_hsharz.api.tracking.dto;

import de.kleemann.co2_hsharz.core.tracking.stats.Statistic;
import lombok.Data;

/**
 * This Class is a Data Transfer Object (DTO) for the {@link Statistic}. <br>
 * It contains fields for all possible subclasses of {@link Statistic}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Data
public class StatisticDTO {

    private String statisticId;
    private String statisticType;

    private String buttonClickName;
    private int buttonClickAmount;

    private String subPageVisitName;
    private int subPageVisitTotalVisits;

    private String totalDurationName;
    private long totalDurationAmount;

}
