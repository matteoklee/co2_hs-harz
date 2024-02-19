package de.kleemann.co2_hsharz.persistence.group.emission;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "GroupEmissionPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupEmissionPersistenceService {

    private final GroupEmissionRepository groupEmissionRepository;

    public GroupEmissionPersistenceService(final GroupEmissionRepository groupEmissionRepository) {
        this.groupEmissionRepository = groupEmissionRepository;
    }

    public List<GroupEmissionEntity> findAllGroupEmissions() {
        return new ArrayList<>(groupEmissionRepository.findAll());
    }

    public GroupEmissionEntity findGroupEmissionById(long groupEmissionId) {
        return groupEmissionRepository.findById(groupEmissionId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown group emission entity with id: " + groupEmissionId));
    }

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

    private GroupEmissionEntity saveGroupEmission(final GroupEmissionEntity groupEmissionEntity) {
        try {
            return groupEmissionRepository.save(groupEmissionEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("groupEmissionEntity already exists.");
        }
    }

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

    public void deleteGroupEmission(final GroupEmissionEntity groupEmissionEntity) {
        if (groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null!");
        }
        groupEmissionRepository.deleteById(groupEmissionEntity.getGroupEmissionId());
    }

    public GroupEmissionEntity createGroupEmissionEntity() {
        return new GroupEmissionEntity();
    }

    public GroupEmissionEntity createGroupEmissionEntity(long id) {
        return new GroupEmissionEntity(id);
    }

}
