package de.kleemann.co2_hsharz.api.tracking.dto;

import java.util.List;

import lombok.Data;

/**
 * This class is a Data Transfer Object (DTO) for a Visitor Request. <br>
 * It contains only a {@link List} of {@link StatisticDTO}s
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Data
public class VisitorStatsRequestDTO {
    List<StatisticDTO> visitorStats;
}
