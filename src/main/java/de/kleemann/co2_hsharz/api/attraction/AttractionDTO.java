package de.kleemann.co2_hsharz.api.attraction;

import lombok.Data;

/**
 * This class is a Data Transfer Object for the {@link AttractionController} API Endpoint
 * 
 * This class is deprecated and pending removal
 * 
 * @author Matteo Kleemann
 * @version 1.0
 */
@Data
@Deprecated
public class AttractionDTO {
    private long attractionId;
    private String attractionName;
    private String attractionLocation;
}
