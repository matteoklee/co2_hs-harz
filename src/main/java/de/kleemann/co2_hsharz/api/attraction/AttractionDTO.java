package de.kleemann.co2_hsharz.api.attraction;

/**
 * This class is a Data Transfer Object for the {@link AttractionController} API Endpoint
 * 
 * @author Matteo Kleemann
 * @version 1.0
 */
public class AttractionDTO {
	
    private long attractionId;
    private String attractionName;
    private String attractionLocation;

    /**
     * Default Constructor. 
     * Use Setters to insert values
     */
    public AttractionDTO() {

    }

	/**
	 * Required Args Constructor. 
	 * @param attractionId
	 * @param attractionLocation
	 */
    public AttractionDTO(String attractionName, String attractionLocation) {
        this.attractionName = attractionName;
        this.attractionLocation = attractionLocation;
    }

    /**
     * Getter for {@code AttractionDTO#attractionId}
     * @return {@code long} ID of this Attraction
     */
    public long getAttractionId() {
        return attractionId;
    }

    /**
     * Setter for {@code AttractionDTO#attractionId}
     * @param attractionId - New ID of this Attraction
     */
    public void setAttractionId(long attractionId) {
        this.attractionId = attractionId;
    }
    
    /**
     * Getter for {@code AttractionDTO#attractionName}
     * @return {@link String} Name of this Attraction
     */
    public String getAttractionName() {
        return attractionName;
    }

    /**
     * Setter for {@code AttractionDTO#attractionName}
     * @param attractionName - New Name of this Attraction
     */
    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    /**
     * Getter for {@code AttractionDTO#attractionLocation}
     * @return {@link String} Location of this Attraction
     */
    public String getAttractionLocation() {
        return attractionLocation;
    }

    /**
     * Setter for {@code AttractionDTO#attractionLocation}
     * @param attractionLocation - New Location of this Attraction
     */
    public void setAttractionLocation(String attractionLocation) {
        this.attractionLocation = attractionLocation;
    }
}
