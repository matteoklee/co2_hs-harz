package de.kleemann.co2_hsharz.persistence.tracking;

import de.kleemann.co2_hsharz.persistence.tracking.stats.StatisticEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.SubPageVisitEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "StatisticEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
public class VisitorStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long visitorStatsId;
    //private List<StatisticEntity> visitorStats;
    @OneToMany
    private List<StatisticEntity> visitorStats;

    public VisitorStatsEntity() {

    }

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

    public long getVisitorStatsId() {
        return visitorStatsId;
    }

    public void setVisitorStatsId(long visitorStatsId) {
        this.visitorStatsId = visitorStatsId;
    }

    public List<StatisticEntity> getVisitorStats() {
        return visitorStats;
    }

    public void setVisitorStats(List<StatisticEntity> visitorStats) {
        this.visitorStats = visitorStats;
    }

}
