package de.kleemann.co2_hsharz.api;

import de.kleemann.co2_hsharz.core.Attraction;
import de.kleemann.co2_hsharz.core.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<AttractionDTO>> getAllAttractions() {
        final List<AttractionDTO> attractions = attractionService.findAllAttractions()
                .stream()
                .map(attraction -> attractionMapper.mapToAttractionDTO(attraction))
                .collect(Collectors.toList());
        return ResponseEntity.ok(attractions);
    }

    @GetMapping(ATTRACTION_URL + "/{id}")
    public ResponseEntity<AttractionDTO> getAttractionById(@PathVariable(value = "id") long attractionId) {
        final Attraction attraction = attractionService.findAttractionById(attractionId);
        return ResponseEntity.ok(attractionMapper.mapToAttractionDTO(attraction));
    }

    @PutMapping(ATTRACTION_URL + "/{id}")
    public ResponseEntity<AttractionDTO> updateAttraction(@PathVariable(value = "id") long attractionId,
                                                       @RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new IllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attraction = attractionMapper.mapToAttraction(attractionDTO);
        AttractionDTO responseAttraction = attractionMapper.mapToAttractionDTO(attractionService.updateAttraction(attractionId, attraction));
        return ResponseEntity.ok(responseAttraction);
    }

    @DeleteMapping(ATTRACTION_URL + "/{id}")
    public void deleteAttraction(@PathVariable(value = "id") long attractionId) {
        Attraction attraction = attractionService.findAttractionById(attractionId);
        attractionService.deleteAttraction(attraction);
    }

    @PostMapping(ATTRACTION_URL)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<AttractionDTO> createAttraction(@RequestBody AttractionDTO attractionDTO) {
        if(attractionDTO == null) {
            throw new IllegalArgumentException("attractionDTO must not be null.");
        }
        Attraction attraction = attractionMapper.mapToAttraction(attractionDTO);
        final Attraction persistedAttraction = attractionService.persistAttraction(attraction);
        return ResponseEntity.ok(attractionMapper.mapToAttractionDTO(persistedAttraction));
    }

}
