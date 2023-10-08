package de.kleemann.co2_hsharz.api;

import de.kleemann.co2_hsharz.core.Attraction;
import de.kleemann.co2_hsharz.core.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class AttractionsController {

    private final String ATTRACTION_URL = "/attractions";
    private final AttractionService attractionService;
    private final AttractionMapper attractionMapper;

    AttractionsController(AttractionService attractionService, AttractionMapper attractionMapper) {
        this.attractionService = attractionService;
        this.attractionMapper = attractionMapper;
    }

    @GetMapping(ATTRACTION_URL)
    public ResponseEntity<List<Attraction>> getAllAttractions() {
        final List<Attraction> attractions = attractionService.findAllAttractions();
        return ResponseEntity.ok(attractions);
    }

    @GetMapping(ATTRACTION_URL + "/{id}")
    public ResponseEntity<Attraction> getAttractionById(@PathVariable(value = "id") long attractionId) {
        final Attraction attraction = attractionService.findAttractionById(attractionId);
        return ResponseEntity.ok(attraction);
    }

    @PutMapping(ATTRACTION_URL + "/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable(value = "id") long attractionId,
                                                       @RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new IllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attraction = attractionMapper.map(attractionDTO);
        return ResponseEntity.ok(attractionService.updateAttraction(attractionId, attraction));
    }

    @DeleteMapping(ATTRACTION_URL + "/{id}")
    public void deleteAttraction(@PathVariable(value = "id") long attractionId) {
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attractionService.deleteAttraction(attraction);
    }

    @PostMapping(ATTRACTION_URL)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Attraction> createAttraction(@RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new IllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionMapper.map(attractionDTO);
        final Attraction persistedAttraction = attractionService.persistAttraction(attraction);
        return ResponseEntity.ok(persistedAttraction);
    }

}