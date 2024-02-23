package de.kleemann.co2_hsharz.api.tracking.dto;

import lombok.Data;

/**
 * Class "StatisticDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Data
public class StatisticDTO {

    private String statisticEntityId;
    private String statisticEntityType;

    private String buttonClickEntityName;
    private int buttonClickEntityAmount;

    private String subPageVisitEntityName;
    private int subPageVisitEntityTotalVisits;

    private String totalDurationEntityName;
    private long totalDurationEntityAmount;

}
