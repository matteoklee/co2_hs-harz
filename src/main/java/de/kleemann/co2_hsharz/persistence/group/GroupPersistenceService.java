package de.kleemann.co2_hsharz.persistence.group;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;

/**
 * This Service provides persistence layer crud functionality for the {@link GroupEntity}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupPersistenceService {

	/** {@link GroupRepository} */
    private final GroupRepository groupRepository;

    /**
     * Constructs a new {@link GroupPersistenceService}
     * @param groupRepository - {@link GroupRepository} used to for database queries
     */
    public GroupPersistenceService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    /**
     * Retrieves a {@link List} of all {@link GroupEntity}s
     * @return {@link List} of {@link GroupEntity}s
     */
    public List<GroupEntity> findAllGroups() {
        return new ArrayList<>(groupRepository.findAll());
    }

    /**
     * Retrieves a {@link GroupEntity} by its ID
     * @param groupId {@link Long} ID
     * @return {@link GroupEntity} with this ID
     */
    public GroupEntity findGroupById(long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown group entity with id: " + groupId));
    }

    /**
     * Retrieves a {@link GroupEntity} by its nickname, passphrase and size
     * @param groupNickName {@link String}
     * @param groupPassPhrase {@link String}
     * @param groupSize {@link Integer}
     * @return {@link GroupEntity} with the given attributes
     */
    public GroupEntity findGroupByNickNameAndPassPhraseAndSize(String groupNickName, String groupPassPhrase, int groupSize) {
        return groupRepository.findByGroupNickNameAndGroupPassPhraseAndGroupSize(groupNickName, groupPassPhrase, groupSize);
    }

    /**
     * Updates a {@link GroupEntity}
     * @param groupId {@link Long} ID of Entity that need updating
     * @param groupEntity {@link GroupEntity} updated entity
     * @return Updated {@link GroupEntity}
     */
    public GroupEntity updateGroup(long groupId, GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null.");
        }
        GroupEntity updateGroupEntity = findGroupById(groupId);
        updateGroupEntity.setGroupId(groupId);
        updateGroupEntity.setGroupNickName(groupEntity.getGroupNickName());
        updateGroupEntity.setGroupPassPhrase(groupEntity.getGroupPassPhrase());
        updateGroupEntity.setGroupSize(groupEntity.getGroupSize());

        return saveGroup(updateGroupEntity);
    }

    /**
     * Saves a {@link GroupEntity} to database
     * @param groupEntity {@link GroupEntity} that should be saved
     * @return saved {@link GroupEntity}
     */
    private GroupEntity saveGroup(final GroupEntity groupEntity) {
        try {
            return groupRepository.save(groupEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("groupEntity already exists.");
        }
    }

    /**
     * Persists a {@link GroupEntity}
     * @param groupEntity {@link GroupEntity} that should be persisted
     * @return persisted {@link GroupEntity}
     */
    public GroupEntity persistGroup(final GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null.");
        }

        return saveGroup(groupEntity);
    }

    /**
     * Deletes a {@link GroupEntity}
     * @param groupEntity {@link GroupEntity}
     */
    public void deleteGroup(final GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null!");
        }
        groupRepository.deleteById(groupEntity.getGroupId());
    }

    /**
     * Creates a new {@link GroupEntity}
     * @return new {@link GroupEntity}
     */
    public GroupEntity createGroupEntity() {
        return new GroupEntity();
    }

    /**
     * Creates a new {@link GroupEntity} with a given id
     * @param id {@link Long} ID
     * @return new {@link GroupEntity} with this id
     */
    public GroupEntity createGroupEntity(long id) {
        return new GroupEntity(id);
    }
}
