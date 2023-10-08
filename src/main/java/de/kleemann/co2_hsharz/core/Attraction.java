package de.kleemann.co2_hsharz.core;

import de.kleemann.co2_hsharz.persistence.AttractionEntity;

public class Attraction {

    private final AttractionEntity attractionEntity;

    public Attraction(final AttractionEntity attractionEntity) {
        if(attractionEntity == null) {
            throw new IllegalArgumentException("attractionEntity may not be null.");
        }
        this.attractionEntity = attractionEntity;
    }

    public AttractionEntity getAttractionEntity() {
        return attractionEntity;
    }

    public Long getAttractionId() {
        return this.attractionEntity.getAttractionId();
    }

    public void setAttractionId(long attractionId) {
        this.attractionEntity.setAttractionId(attractionId);
    }

    public String getAttractionName() {
        return this.attractionEntity.getAttractionName();
    }

    public void setAttractionName(String attractionName) {
        this.attractionEntity.setAttractionName(attractionName);
    }

    public String getAttractionLocation() {
        return this.attractionEntity.getAttractionLocation();
    }

    public void setAttractionLocation(String attractionLocation) {
        this.attractionEntity.setAttractionLocation(attractionLocation);
    }

}
