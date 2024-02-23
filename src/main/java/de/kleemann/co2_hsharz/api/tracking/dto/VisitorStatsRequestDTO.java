package de.kleemann.co2_hsharz.api.tracking.dto;

import java.util.List;

/**
 * Class "VisitorStatsRequestDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
public class VisitorStatsRequestDTO {

    List<StatisticDTO> visitorStats;

    public VisitorStatsRequestDTO() {

    }

    public VisitorStatsRequestDTO(List<StatisticDTO> visitorStats) {
        this.visitorStats = visitorStats;
    }

    public List<StatisticDTO> getVisitorStats() {
        return visitorStats;
    }
}
