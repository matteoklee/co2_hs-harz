package de.kleemann.co2_hsharz.api.tracking;

import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsEntity;
import de.kleemann.co2_hsharz.persistence.tracking.VisitorStatsPersistenceService;
import de.kleemann.co2_hsharz.persistence.tracking.stats.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "TrackingController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@RestController
@RequestMapping("/api")
public class TrackingController {

    private final VisitorStatsPersistenceService visitorStatsPersistenceService;
    private final StatisticPersistenceService statisticPersistenceService;

    public TrackingController(final VisitorStatsPersistenceService visitorStatsPersistenceService,
                              final StatisticPersistenceService statisticPersistenceService) {
        this.visitorStatsPersistenceService = visitorStatsPersistenceService;
        this.statisticPersistenceService = statisticPersistenceService;
    }

     /*

        {
            "visitorStatsId": "",
            "visitorStats": [
                {
                    statisticEntityId: "",
                    statisticEntityType: "",
                    subPageVisitEntityName: "",
                    subPageVisitEntityTotalVisits: "",
                },
                {
                    statisticEntityId: "",
                    statisticEntityType: "",
                    subPageVisitEntityName: "",
                    subPageVisitEntityTotalVisits: "",
                },
            ]
        }
     */

    @PostMapping("/statistics")
    public ResponseEntity<VisitorStatsEntity> saveStatistics(@RequestBody VisitorStatsRequestDTO visitorStatsRequestDTO) {
        VisitorStatsEntity visitorStatsEntity = visitorStatsPersistenceService.createVisitorStatsEntity();

        List<StatisticEntity> visitorStats = new ArrayList<>();
        for(StatisticDTO statisticDTO : visitorStatsRequestDTO.getVisitorStats()) {
            String type = statisticDTO.getStatisticEntityType();

            switch (type) {
                case "subPageVisit":
                    SubPageVisitEntity subPageVisitEntity = (SubPageVisitEntity) statisticPersistenceService.createStatisticEntity(type);
                    subPageVisitEntity.setSubPageVisitEntityName(statisticDTO.getSubPageVisitEntityName());
                    subPageVisitEntity.setSubPageVisitEntityTotalVisits(statisticDTO.getSubPageVisitEntityTotalVisits());
                    SubPageVisitEntity persistedSubPageVisitEntity = (SubPageVisitEntity) statisticPersistenceService.persistStatistic(subPageVisitEntity);
                    visitorStats.add(persistedSubPageVisitEntity);
                    break;
                case "totalDuration":
                    TotalDurationEntity totalDurationEntity = (TotalDurationEntity) statisticPersistenceService.createStatisticEntity(type);
                    totalDurationEntity.setTotalDurationEntityName(statisticDTO.getTotalDurationEntityName());
                    totalDurationEntity.setTotalDurationEntityAmount(statisticDTO.getTotalDurationEntityAmount());
                    TotalDurationEntity persistedTotalDurationEntity = (TotalDurationEntity) statisticPersistenceService.persistStatistic(totalDurationEntity);
                    visitorStats.add(persistedTotalDurationEntity);
                    break;
                case "buttonClick":
                    ButtonClickEntity buttonClickEntity = (ButtonClickEntity) statisticPersistenceService.createStatisticEntity(type);
                    buttonClickEntity.setButtonClickEntityName(statisticDTO.getButtonClickEntityName());
                    buttonClickEntity.setButtonClickEntityAmount(statisticDTO.getButtonClickEntityAmount());
                    ButtonClickEntity persistedButtonClickEntity = (ButtonClickEntity) statisticPersistenceService.persistStatistic(buttonClickEntity);
                    visitorStats.add(persistedButtonClickEntity);
                    break;
                default: continue;
            }
        }
        visitorStatsEntity.setVisitorStats(visitorStats);

        final VisitorStatsEntity persistedVisitorStatsEntity = visitorStatsPersistenceService.persistVisitorStats(visitorStatsEntity);
        return ResponseEntity.ok(persistedVisitorStatsEntity);
    }


}
