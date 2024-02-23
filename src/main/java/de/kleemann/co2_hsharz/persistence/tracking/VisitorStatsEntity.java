package de.kleemann.co2_hsharz.persistence.tracking;

import java.util.List;

import de.kleemann.co2_hsharz.core.tracking.VisitorStats;
import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Entity represents a {@link VisitorStats} (a collection of statistics)
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Data
@Entity
@NoArgsConstructor
public class VisitorStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long visitorStatsId;
    
    @OneToMany
    private List<StatisticEntity> visitorStats;

    public VisitorStatsEntity(long visitorStatsId) {
        setVisitorStatsId(visitorStatsId);
    }

    /*
        VisitorStatsEntity visitorStats = new VisitorStatsEntity();

        visitorStats.setVisitorStatsId(1);//auto increment
        List<StatisticEntity> currentVisitorStats = new ArrayList<>();

        SubPageVisitEntity newSubPageVisitEntity = new SubPageVisitEntity();
        newSubPageVisitEntity.setStatisticEntityId(5);//auto
        newSubPageVisitEntity.setStatisticEntityType("");
        newSubPageVisitEntity.setSubPageVisitEntityName("");
        newSubPageVisitEntity.setSubPageVisitEntityTotalVisits(5);

        currentVisitorStats.add(newSubPageVisitEntity);
        visitorStats.setVisitorStats(currentVisitorStats);
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
}
