package de.kleemann.co2_hsharz.core.tracking.stats;

import de.kleemann.co2_hsharz.persistence.tracking.stats.ButtonClickEntity;
import de.kleemann.co2_hsharz.persistence.tracking.stats.SubPageVisitEntity;
import lombok.NonNull;

/**
 * This Class is a specialization of the {@link StatisticImpl} and tracks sub page visits in the frontend
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 14.02.2024
 */
public class SubPageVisit extends StatisticImpl {

	/** {@link ButtonClickEntity} used to create this Object */
    private SubPageVisitEntity subPageVisitEntity;

    /**
     * Constructs a new {@link SubPageVisit} Object
     * @param subPageVisitEntity {@link SubPageVisitEntity} used to create this object
     */
    public SubPageVisit(@NonNull SubPageVisitEntity subPageVisitEntity){
        super(subPageVisitEntity);
        this.subPageVisitEntity = subPageVisitEntity;
    }

    /**
     * Returns the Name of the subpage
     * @return {@link String} subpage name
     */
    public String getSubPageVisitName() {
        return this.subPageVisitEntity.getSubPageVisitEntityName();
    }

    /**
     * Sets the Name of the subpage
     * @param subPageVisitName {@link String} new subpage name
     */
    public void setSubPageVisitName(String subPageVisitName) {
        this.subPageVisitEntity.setSubPageVisitEntityName(subPageVisitName);
    }

    /**
     * Returns the total amount of visits for a subpage
     * @return {@link Integer} amount
     */
    public int getSubPageVisitTotalVisits() {
        return this.subPageVisitEntity.getSubPageVisitEntityTotalVisits();
    }

    /**
     * Sets the total amount of visits for a subpage
     * @param subPageVisitTotalVisits {@link Integer} new amount
     */
    public void setSubPageVisitTotalVisits(int subPageVisitTotalVisits) {
        this.subPageVisitEntity.setSubPageVisitEntityTotalVisits(subPageVisitTotalVisits);
    }
}
