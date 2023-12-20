package de.kleemann.co2_hsharz.api.attraction;

import de.kleemann.co2_hsharz.core.attraction.Attraction;
import de.kleemann.co2_hsharz.core.attraction.AttractionService;
import lombok.NonNull;

import org.springframework.stereotype.Component;

/**
 * This class is used to map an {@link AttractionDTO} to an {@link Attraction}
 */
@Component
public class AttractionMapper {

    private final AttractionService attractionService;

    /**
     * Constructor with Auto-Injection of {@link AttractionService}
     * @param attractionService - {@link AttractionService}-Bean
     */
    public AttractionMapper(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    /**
     * Maps an {@link AttractionDTO} to an {@link Attraction}
     * @param attractionDTO - {@link AttractionDTO}-Object to map to an {@link Attraction}-Object
     * @return Corresponding {@link Attraction}-Object for {@code attractionDTO}
     */
    public Attraction mapToAttraction(@NonNull AttractionDTO attractionDTO) {
        Attraction attraction = attractionService.createAttraction();
        attraction.setAttractionName(attractionDTO.getAttractionName());
        attraction.setAttractionLocation(attractionDTO.getAttractionLocation());
        return attraction;
    }

    /**
     * Maps an {@link Attraction} to an {@link AttractionDTO}
     * @param attraction - {@link Attraction}-Object to map to an {@link AttractionDTO}-Object
     * @return Corresponding {@link AttractionDTO}-Object for {@code attraction}
     */
    public AttractionDTO mapToAttractionDTO(@NonNull Attraction attraction) {
        AttractionDTO attractionDTO = new AttractionDTO();
        attractionDTO.setAttractionId(attraction.getAttractionId());
        attractionDTO.setAttractionName(attraction.getAttractionName());
        attractionDTO.setAttractionLocation(attraction.getAttractionLocation());
        return attractionDTO;
    }

}
