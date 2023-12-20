package de.kleemann.co2_hsharz.core.attraction;

import de.kleemann.co2_hsharz.persistence.attraction.AttractionEntity;
import lombok.NonNull;

/**
 * An Attraction is a place worth visiting. Attractions are saved as Entities in our Database. 
 * This class reflects a AttractionEntity in the core layer of the project
 */
public class Attraction {

	/**
	 * {@link AttractionEntity} upon which this {@link Attraction} was constructed
	 */
    private final AttractionEntity attractionEntity;

    /**
     * Constructs a new {@link Attraction} from an {@link AttractionEntity}
     * @param attractionEntity - {@link AttractionEntity}
     * @throws IllegalArgumentException if {@code attractionEntity} is null
     */
    public Attraction(@NonNull final AttractionEntity attractionEntity) {
        if(attractionEntity == null) {
            throw new IllegalArgumentException("attractionEntity may not be null.");
        }
        this.attractionEntity = attractionEntity;
    }

    /**
     * Getter for {@code Attraction#attractionEntity}
     * @return {@link AttractionEntity} used to construct this Attraction
     */
    public AttractionEntity getAttractionEntity() {
        return attractionEntity;
    }

    /**
     * Getter for the ID of this Attraction
     * @return {@link Long} Id of this Attraction
     */
    public Long getAttractionId() {
        return this.attractionEntity.getAttractionId();
    }

    /**
     * Setter for the ID of this Attraction
     * @param attractionId - {@link Long} New ID of this Attraction
     */
    public void setAttractionId(long attractionId) {
        this.attractionEntity.setAttractionId(attractionId);
    }

    /**
     * Getter for the Name of this Attraction
     * @return {@link String} Name of this Attraction
     */
    public String getAttractionName() {
        return this.attractionEntity.getAttractionName();
    }

    /**
     * Setter for the Name of this Attraction
     * @param attractionName - {@link String} New Name of this Attraction
     */
    public void setAttractionName(String attractionName) {
        this.attractionEntity.setAttractionName(attractionName);
    }

    /**
     * Getter for the Location of this Attraction
     * @return {@link String} Location of this Attraction
     */
    public String getAttractionLocation() {
        return this.attractionEntity.getAttractionLocation();
    }

    /**
     * Setter for the Location of this Attraction
     * @param attractionLocation - {@link String} New Location of this Attraction
     */
    public void setAttractionLocation(String attractionLocation) {
        this.attractionEntity.setAttractionLocation(attractionLocation);
    }

}
