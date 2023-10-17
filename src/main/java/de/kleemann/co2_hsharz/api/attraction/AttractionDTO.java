package de.kleemann.co2_hsharz.api.attraction;

public class AttractionDTO {

    private long attractionId;
    private String attractionName;
    private String attractionLocation;

    public AttractionDTO() {

    }

    public AttractionDTO(long attractionId, String attractionLocation) {
        this.attractionId = attractionId;
        this.attractionLocation = attractionLocation;
    }

    public long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(long attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionLocation() {
        return attractionLocation;
    }

    public void setAttractionLocation(String attractionLocation) {
        this.attractionLocation = attractionLocation;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }
}
