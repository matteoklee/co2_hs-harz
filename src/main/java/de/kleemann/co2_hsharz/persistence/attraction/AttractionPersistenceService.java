package de.kleemann.co2_hsharz.persistence.attraction;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttractionPersistenceService {

    private final AttractionRepository attractionRepository;

    public AttractionPersistenceService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<AttractionEntity> findAllAttractions() {
        return new ArrayList<>(attractionRepository.findAll());
    }

    public AttractionEntity findAttractionById(long attractionId) {
        return attractionRepository.findById(attractionId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown attraction with id: " + attractionId));
    }

    public AttractionEntity updateAttraction(long attractionId, AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        AttractionEntity updateAttractionEntity = findAttractionById(attractionId);
        updateAttractionEntity.setAttractionId(attractionId);
        updateAttractionEntity.setAttractionName(attractionEntity.getAttractionName());
        updateAttractionEntity.setAttractionLocation(attractionEntity.getAttractionLocation());
        return saveAttraction(updateAttractionEntity);
    }

    private AttractionEntity saveAttraction(final AttractionEntity attractionEntity) {
        try {
            return attractionRepository.save(attractionEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("attraction already exists.");
        }
    }

    public AttractionEntity persistAttraction(final AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null.");
        }
        /*
        if (attractionEntity.getAttractionId() != null) {
            throw new IllegalArgumentException("new attraction must not contain an id.");
        }
        */
        return saveAttraction(attractionEntity);
    }

    public void deleteAttraction(final AttractionEntity attractionEntity) {
        if (attractionEntity == null) {
            throw new CustomIllegalArgumentException("attraction must not be null!");
        }
        attractionRepository.deleteById(attractionEntity.getAttractionId());
    }

    public AttractionEntity createAttractionEntity() {
        return new AttractionEntity();
    }

    public AttractionEntity createAttractionEntity(long id) {
        return new AttractionEntity(id);
    }

}
