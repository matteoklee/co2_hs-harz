package de.kleemann.co2_hsharz.api.attraction;

import de.kleemann.co2_hsharz.core.attraction.Attraction;
import de.kleemann.co2_hsharz.core.attraction.AttractionService;
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
