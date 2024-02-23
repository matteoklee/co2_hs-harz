package de.kleemann.co2_hsharz.persistence.group.emission;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmission;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This Service provides CRUD-Functionality for {@link GroupEmission}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupEmissionPersistenceService {

	/** {@link GroupEmissionRepository} */
    private final GroupEmissionRepository groupEmissionRepository;

    /**
     * Constructs a new {@link GroupEmissionPersistenceService}
     * @param groupEmissionRepository {@link GroupEmissionRepository} used for database queries
     */
    public GroupEmissionPersistenceService(final GroupEmissionRepository groupEmissionRepository) {
        this.groupEmissionRepository = groupEmissionRepository;
    }

    /**
     * Retrieves a {@link List} of all {@link GroupEmissionEntity}s
     * @return {@link List} of {@link GroupEmissionEntity}s
     */
    public List<GroupEmissionEntity> findAllGroupEmissions() {
        return new ArrayList<>(groupEmissionRepository.findAll());
    }

    /**
     * Retrieves a {@link GroupEmissionEntity} by its ID
     * @param groupEmissionId {@link Long} id
     * @return possibly <code>null</code> {@link GroupEmissionEntity} with this id
     */
    public GroupEmissionEntity findGroupEmissionById(long groupEmissionId) {
        return groupEmissionRepository.findById(groupEmissionId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown group emission entity with id: " + groupEmissionId));
    }

    /**
     * Updates a {@link GroupEmissionEntity}
     * @param groupEmissionId {@link Long} Id of the {@link GroupEmissionEntity} that should be updated
     * @param groupEmissionEntity Updated {@link GroupEmissionEntity}
     * @return Updated {@link GroupEmissionEntity}
     */
    public GroupEmissionEntity updateGroupEmission(long groupEmissionId, GroupEmissionEntity groupEmissionEntity) {
        if (groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null.");
        }
        GroupEmissionEntity updateGroupEmissionEntity = findGroupEmissionById(groupEmissionId);
        updateGroupEmissionEntity.setGroupEmissionId(groupEmissionId);
        updateGroupEmissionEntity.setGroupEmissionStartLocation(groupEmissionEntity.getGroupEmissionStartLocation());
        updateGroupEmissionEntity.setGroupEmissionEndLocation(groupEmissionEntity.getGroupEmissionEndLocation());
        updateGroupEmissionEntity.setGroupEmissionCustomTransportMedium(groupEmissionEntity.isGroupEmissionCustomTransportMedium());
        updateGroupEmissionEntity.setGroupEmissionTransportMedium(groupEmissionEntity.getGroupEmissionTransportMedium());
        updateGroupEmissionEntity.setGroup(groupEmissionEntity.getGroup());
        updateGroupEmissionEntity.setGroupEmission(groupEmissionEntity.getGroupEmission());
        updateGroupEmissionEntity.setGroupEmissionScore(groupEmissionEntity.getGroupEmissionScore());
        updateGroupEmissionEntity.setGroupEmissionCreateDate(groupEmissionEntity.getGroupEmissionCreateDate());


        return saveGroupEmission(updateGroupEmissionEntity);
    }

    /**
     * Saves a {@link GroupEmissionEntity}
     * @param groupEmissionEntity
     * @return
     */
    private GroupEmissionEntity saveGroupEmission(final GroupEmissionEntity groupEmissionEntity) {
        try {
            return groupEmissionRepository.save(groupEmissionEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("groupEmissionEntity already exists.");
        }
    }

    /**
     * Persists a {@link GroupEmissionEntity}
     * @param groupEmissionEntity {@link GroupEmissionEntity} to persist
     * @return Persisted {@link GroupEmissionEntity}
     */
    public GroupEmissionEntity persistGroupEmission(final GroupEmissionEntity groupEmissionEntity) {
        if (groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null.");
        }
    /*
    if (attractionEntity.getAttractionId() != null) {
        throw new IllegalArgumentException("new attraction must not contain an id.");
    }
    */
        return saveGroupEmission(groupEmissionEntity);
    }

    /**
     * Deletes a {@link GroupEmissionEntity}
     * @param groupEmissionEntity {@link GroupEmissionEntity} to delete
     */
    public void deleteGroupEmission(final GroupEmissionEntity groupEmissionEntity) {
        if (groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null!");
        }
        groupEmissionRepository.deleteById(groupEmissionEntity.getGroupEmissionId());
    }

    /**
     * Creates a new {@link GroupEmissionEntity}
     * @return new {@link GroupEmissionEntity}
     */
    public GroupEmissionEntity createGroupEmissionEntity() {
        return new GroupEmissionEntity();
    }

    /**
     * Creates a new {@link GroupEmissionEntity} with a specific id
     * @param id {@link Long} id
     * @return new {@link GroupEmissionEntity}
     */
    public GroupEmissionEntity createGroupEmissionEntity(long id) {
        return new GroupEmissionEntity(id);
    }

}
