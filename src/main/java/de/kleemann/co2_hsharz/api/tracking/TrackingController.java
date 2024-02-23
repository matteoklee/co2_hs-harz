package de.kleemann.co2_hsharz.api.tracking;

import de.kleemann.co2_hsharz.api.tracking.dto.StatisticDTO;
import de.kleemann.co2_hsharz.api.tracking.dto.VisitorStatsRequestDTO;
import de.kleemann.co2_hsharz.core.tracking.VisitorStats;
import de.kleemann.co2_hsharz.core.tracking.VisitorStatsImpl;
import de.kleemann.co2_hsharz.core.tracking.VisitorStatsService;
import de.kleemann.co2_hsharz.core.tracking.stats.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This API Controller offers the API Endpoint {@code /statistics} which can be used to save Frontend-Statistics, like visitor interactions or the duration of the visit. <br>
 * See the API Documentation at <a href=https://github.com/matteoklee/co2_hs-harz/wiki/Statistics-Endpoint> Github </a> for detailed information
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@RestController
@RequestMapping("/api")
public class TrackingController {

	/** {@link VisitorStatsService} - used to save visitor stats*/
    private final VisitorStatsService visitorStatsService;
    /** {@link StatisticService} - used to create and save statistics*/
    private final StatisticService statisticService;

    /**
     * Constructs a new {@link TrackingController}
     * @param visitorStatsService - {@link VisitorStatsService} used to save visitor stats
     * @param statisticService - {@link StatisticService} used to create and save statistics
     */
    public TrackingController(VisitorStatsService visitorStatsService, StatisticService statisticService) {
        this.visitorStatsService = visitorStatsService;
        this.statisticService = statisticService;
    }

    /**
     * Stores Visitor Statistics in Database
     * Expected JSON Object:
     * <pre>
     * {
     *      "visitorStatsId": "",
     *      "visitorStats": [
     *          {
     *              statisticEntityId: "",
     *              statisticEntityType: "",
     *              subPageVisitEntityName: "",
     *              subPageVisitEntityTotalVisits: "",
     *          },
     *          {
     *              statisticEntityId: "",
     *              statisticEntityType: "",
     *              subPageVisitEntityName: "",
     *              subPageVisitEntityTotalVisits: "",
     *          },
     *      ]
     *  }
     *  </pre>
     * 
     * @param visitorStatsRequestDTO - {@link VisitorStatsRequestDTO} containing the statistic values to be saved
     * @return {@link ResponseEntity} containing the saved {@link VisitorStatsImpl}
     * 
     * 
     */
    @PostMapping("/statistics")
    public ResponseEntity<VisitorStats> saveStatistics(@RequestBody VisitorStatsRequestDTO visitorStatsRequestDTO) {
        System.out.println("CALLING /statistics");

        VisitorStats visitorStatsImpl = visitorStatsService.createVisitorStatsEntity();

        List<StatisticImpl> visitorStats = new ArrayList<>();
        for(StatisticDTO statisticDTO : visitorStatsRequestDTO.getVisitorStats()) {
            String type = statisticDTO.getStatisticType();
            //StatisticImpl statisticImpl = statisticService.createStatisticEntity(type);
            switch (type) {
                case "subPageVisit":
                    SubPageVisit subPageVisit = (SubPageVisit) statisticService.createStatisticEntity(type);
                    subPageVisit.setSubPageVisitName(statisticDTO.getSubPageVisitName());
                    subPageVisit.setSubPageVisitTotalVisits(statisticDTO.getSubPageVisitTotalVisits());
                    StatisticImpl persistedSubPageVisit = statisticService.persistStatistic(subPageVisit);

                    visitorStats.add(persistedSubPageVisit);
                    break;
                case "totalDuration":
                    System.out.println("totalDuration");
                    TotalDuration totalDuration = (TotalDuration) statisticService.createStatisticEntity(type);
                    totalDuration.setTotalDurationName(statisticDTO.getTotalDurationName());
                    totalDuration.setTotalDurationAmount(statisticDTO.getTotalDurationAmount());
                    StatisticImpl persistedTotalDuration = statisticService.persistStatistic(totalDuration);

                    visitorStats.add(persistedTotalDuration);
                    //System.out.println(persistedTotalDuration.getTotalDurationAmount()/1000 + " s , " + persistedTotalDuration.getTotalDurationAmount()/1000/60 + " min");
                    break;
                case "buttonClick":
                    ButtonClick buttonClick = (ButtonClick) statisticService.createStatisticEntity(type);
                    buttonClick.setButtonClickName(statisticDTO.getButtonClickName());
                    buttonClick.setButtonClickAmount(statisticDTO.getButtonClickAmount());
                    StatisticImpl persistedButtonClick = statisticService.persistStatistic(buttonClick);

                    visitorStats.add(persistedButtonClick);
                    break;
                default:
                    break;
            }
        }
        visitorStatsImpl.setVisitorStats(visitorStats);

        final VisitorStats persistedVisitorStatsImpl = visitorStatsService.persistVisitorStats(visitorStatsImpl);
        return ResponseEntity.ok(persistedVisitorStatsImpl);

        /*
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
                    System.out.println("totalDuration");
                    TotalDurationEntity totalDurationEntity = (TotalDurationEntity) statisticPersistenceService.createStatisticEntity(type);
                    totalDurationEntity.setTotalDurationEntityName(statisticDTO.getTotalDurationEntityName());
                    totalDurationEntity.setTotalDurationEntityAmount(statisticDTO.getTotalDurationEntityAmount());
                    TotalDurationEntity persistedTotalDurationEntity = (TotalDurationEntity) statisticPersistenceService.persistStatistic(totalDurationEntity);
                    visitorStats.add(persistedTotalDurationEntity);

                    System.out.println(persistedTotalDurationEntity.getTotalDurationEntityAmount()/1000 + " s , " + persistedTotalDurationEntity.getTotalDurationEntityAmount()/1000/60 + " min");
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
        */
    }

}
