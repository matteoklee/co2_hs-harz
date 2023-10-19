package de.kleemann.co2_hsharz.api.attraction;

import de.kleemann.co2_hsharz.core.attraction.Attraction;
import de.kleemann.co2_hsharz.core.attraction.AttractionService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
class AttractionController {

    /**
     * URL path for retrieving all attractions.
     */
    private final String ATTRACTION_URL = "/attractions";

    /**
     * URL path for retrieving an attraction by ID.
     */
    private final String ATTRACTION_URL_WITH_ID = "/attractions/{id}";
    private final AttractionService attractionService;
    private final AttractionMapper attractionMapper;

    /**
     * Constructor for AttractionController.
     *
     * @param attractionService The attraction service to be injected.
     * @param attractionMapper The attraction mapper to be injected.
     */
    AttractionController(final AttractionService attractionService, final AttractionMapper attractionMapper) {
        this.attractionService = attractionService;
        this.attractionMapper = attractionMapper;
    }

    /**
     * Get a list of all attractions.
     *
     * @return ResponseEntity containing a list of AttractionDTO objects.
     */
    @GetMapping(ATTRACTION_URL)
    public ResponseEntity<List<AttractionDTO>> getAllAttractions() {
        final List<AttractionDTO> attractions = attractionService.findAllAttractions()
                .stream()
                .map(attraction -> attractionMapper.mapToAttractionDTO(attraction))
                .collect(Collectors.toList());
        return ResponseEntity.ok(attractions);
    }

    /**
     * Get an attraction by its ID.
     *
     * @param attractionId The ID of the attraction to retrieve.
     * @return ResponseEntity containing an AttractionDTO object.
     */
    @GetMapping(ATTRACTION_URL_WITH_ID)
    public ResponseEntity<AttractionDTO> getAttractionById(@PathVariable(value = "id") long attractionId) {
        final Attraction attraction = attractionService.findAttractionById(attractionId);
        return ResponseEntity.ok(attractionMapper.mapToAttractionDTO(attraction));
    }

    /**
     * Update an attraction by its ID.
     *
     * @param attractionId The ID of the attraction to update.
     * @param attractionDTO The updated data in the form of an AttractionDTO.
     * @return ResponseEntity containing the updated AttractionDTO.
     */
    @PutMapping(ATTRACTION_URL_WITH_ID)
    public ResponseEntity<AttractionDTO> updateAttraction(@PathVariable(value = "id") long attractionId,
                                                       @RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new CustomIllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attraction = attractionMapper.mapToAttraction(attractionDTO);
        AttractionDTO responseAttraction = attractionMapper.mapToAttractionDTO(attractionService.updateAttraction(attractionId, attraction));
        return ResponseEntity.ok(responseAttraction);
    }

    /**
     * Delete an attraction by its ID.
     *
     * @param attractionId The ID of the attraction to delete.
     */
    @DeleteMapping(ATTRACTION_URL_WITH_ID)
    public void deleteAttraction(@PathVariable(value = "id") long attractionId) {
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attractionService.deleteAttraction(attraction);
    }

    /**
     * Create a new attraction.
     *
     * @param attractionDTO The data for the new attraction in the form of an AttractionDTO.
     * @return ResponseEntity containing the created AttractionDTO.
     */
    @PostMapping(ATTRACTION_URL)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<AttractionDTO> createAttraction(@RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new CustomIllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionMapper.mapToAttraction(attractionDTO);
        final Attraction persistedAttraction = attractionService.persistAttraction(attraction);
        return ResponseEntity.ok(attractionMapper.mapToAttractionDTO(persistedAttraction));
    }

}
