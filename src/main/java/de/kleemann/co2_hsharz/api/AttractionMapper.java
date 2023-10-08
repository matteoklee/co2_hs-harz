package de.kleemann.co2_hsharz.api;

import de.kleemann.co2_hsharz.core.Attraction;
import de.kleemann.co2_hsharz.core.AttractionService;
import org.springframework.stereotype.Component;

@Component
public class AttractionMapper {

    private final AttractionService attractionService;

    public AttractionMapper(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    public Attraction map(AttractionDTO attractionDTO) {
        Attraction attraction = attractionService.createAttraction();
        attraction.setAttractionName(attractionDTO.getAttractionName());
        attraction.setAttractionLocation(attraction.getAttractionLocation());
        return attraction;
    }
}
