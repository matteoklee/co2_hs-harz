package de.kleemann.co2_hsharz.core;

import de.kleemann.co2_hsharz.api.AttractionDTO;
import de.kleemann.co2_hsharz.persistence.AttractionEntity;
import de.kleemann.co2_hsharz.persistence.AttractionPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionService {

    private final AttractionPersistenceService attractionPersistenceService;

    public AttractionService(AttractionPersistenceService attractionPersistenceService) {
        this.attractionPersistenceService = attractionPersistenceService;
    }

    public List<Attraction> findAllAttractions() {
        return attractionPersistenceService.findAllAttractions()
                .stream()
                .map(Attraction::new)
                .collect(Collectors.toList());
    }

    public Attraction findAttractionById(long attractionId) {
        try {
            return new Attraction(attractionPersistenceService.findAttractionById(attractionId));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public Attraction updateAttraction(long attractionId, Attraction attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("attraction must not be null.");
        }
        final AttractionEntity updatedAttractionEntity;
        try {
            updatedAttractionEntity = attractionPersistenceService.updateAttraction(attractionId, attraction.getAttractionEntity());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new Attraction(updatedAttractionEntity);
    }

    public Attraction persistAttraction(final Attraction attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("attraction must not be null.");
        }
        final AttractionEntity persistedAttractionEntity;
        try {
            persistedAttractionEntity = attractionPersistenceService.persistAttraction(attraction.getAttractionEntity());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new Attraction(persistedAttractionEntity);
    }

    public void deleteAttraction(final Attraction attraction) {
        if (attraction == null) {
            throw new IllegalArgumentException("attraction must not be null.");
        }
        attractionPersistenceService.deleteAttraction(attraction.getAttractionEntity());
    }

    public Attraction createAttraction() {
        return new Attraction(attractionPersistenceService.createAttractionEntity());
    }

    public Attraction createAttraction(long id) {
        return new Attraction(attractionPersistenceService.createAttractionEntity(id));
    }


}
