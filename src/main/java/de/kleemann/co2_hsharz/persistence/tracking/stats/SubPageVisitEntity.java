package de.kleemann.co2_hsharz.persistence.tracking.stats;

import jakarta.persistence.Entity;

/**
 * Class "SubPageVisitEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Entity
public class SubPageVisitEntity extends StatisticEntity {

    private String subPageVisitEntityName;
    private int subPageVisitEntityTotalVisits;

    public SubPageVisitEntity(long id) {
        setStatisticEntityId(id);
    }

    public SubPageVisitEntity() {}

    public String getSubPageVisitEntityName() {
        return subPageVisitEntityName;
    }

    public void setSubPageVisitEntityName(String subPageVisitEntityName) {
        this.subPageVisitEntityName = subPageVisitEntityName;
    }

    public int getSubPageVisitEntityTotalVisits() {
        return subPageVisitEntityTotalVisits;
    }

    public void setSubPageVisitEntityTotalVisits(int subPageVisitEntityTotalVisits) {
        this.subPageVisitEntityTotalVisits = subPageVisitEntityTotalVisits;
    }
}
