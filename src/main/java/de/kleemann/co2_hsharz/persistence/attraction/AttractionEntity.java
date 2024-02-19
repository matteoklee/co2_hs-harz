package de.kleemann.co2_hsharz.persistence.attraction;

import jakarta.persistence.*;

@Entity
public class AttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attractionId;
    private String attractionName;
    private String attractionLocation;

    public AttractionEntity() {

    }

    public AttractionEntity(long id) {
        setAttractionId(id);
    }

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(long attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionLocation() {
        return attractionLocation;
    }

    public void setAttractionLocation(String attractionLocation) {
        this.attractionLocation = attractionLocation;
    }
}
