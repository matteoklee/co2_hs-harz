package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.SubPageVisitEntity;
import lombok.NonNull;

/**
 * Class "SubPageVisit" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class SubPageVisit extends StatisticImpl {

    private SubPageVisitEntity subPageVisitEntity;

    public SubPageVisit(@NonNull SubPageVisitEntity subPageVisitEntity){
        super(subPageVisitEntity);
        this.subPageVisitEntity = subPageVisitEntity;
    }

    public String getSubPageVisitName() {
        return this.subPageVisitEntity.getSubPageVisitEntityName();
    }

    public void setSubPageVisitName(String subPageVisitName) {
        this.subPageVisitEntity.setSubPageVisitEntityName(subPageVisitName);
    }

    public int getSubPageVisitTotalVisits() {
        return this.subPageVisitEntity.getSubPageVisitEntityTotalVisits();
    }

    public void setSubPageVisitTotalVisits(int subPageVisitTotalVisits) {
        this.subPageVisitEntity.setSubPageVisitEntityTotalVisits(subPageVisitTotalVisits);
    }
}
