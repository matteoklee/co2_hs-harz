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

    public Attraction mapToAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = attractionService.createAttraction();
        attraction.setAttractionName(attractionDTO.getAttractionName());
        attraction.setAttractionLocation(attractionDTO.getAttractionLocation());
        return attraction;
    }

    public AttractionDTO mapToAttractionDTO(Attraction attraction) {
        AttractionDTO attractionDTO = new AttractionDTO();
        attractionDTO.setAttractionId(attraction.getAttractionId());
        attractionDTO.setAttractionName(attraction.getAttractionName());
        attractionDTO.setAttractionLocation(attraction.getAttractionLocation());
        return attractionDTO;
    }

}
