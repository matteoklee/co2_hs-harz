package de.kleemann.co2_hsharz.persistence.group;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class "GroupPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupPersistenceService {

    private final GroupRepository groupRepository;

    public GroupPersistenceService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<GroupEntity> findAllGroups() {
        return new ArrayList<>(groupRepository.findAll());
    }

    public GroupEntity findGroupById(long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomEntityNotFoundException("unknown group entity with id: " + groupId));
    }

    public GroupEntity findGroupByNickNameAndPassPhraseAndSize(String groupNickName, String groupPassPhrase, int groupSize) {
        return groupRepository.findByGroupNickNameAndGroupPassPhraseAndGroupSize(groupNickName, groupPassPhrase, groupSize);
    }

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

    private GroupEntity saveGroup(final GroupEntity groupEntity) {
        try {
            return groupRepository.save(groupEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("groupEntity already exists.");
        }
    }

    public GroupEntity persistGroup(final GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null.");
        }

        return saveGroup(groupEntity);
    }

    public void deleteGroup(final GroupEntity groupEntity) {
        if (groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null!");
        }
        groupRepository.deleteById(groupEntity.getGroupId());
    }

    public GroupEntity createGroupEntity() {
        return new GroupEntity();
    }

    public GroupEntity createGroupEntity(long id) {
        return new GroupEntity(id);
    }


}
