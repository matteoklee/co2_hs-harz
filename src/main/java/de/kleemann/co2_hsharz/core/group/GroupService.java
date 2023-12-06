package de.kleemann.co2_hsharz.core.group;

import de.kleemann.co2_hsharz.core.attraction.Attraction;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.attraction.AttractionEntity;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.group.GroupPersistenceService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "GroupService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupService {

    private final GroupPersistenceService groupPersistenceService;

    public GroupService(final GroupPersistenceService groupPersistenceService) {
        this.groupPersistenceService = groupPersistenceService;
    }

    public List<GroupImpl> findAllGroups() {
        return groupPersistenceService.findAllGroups()
                .stream()
                .map(GroupImpl::new)
                .collect(Collectors.toList());
    }

    public GroupImpl findGroupById(long groupId) {
        try {
            return new GroupImpl(groupPersistenceService.findGroupById(groupId));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findGroupById()");
        }
    }

    public GroupImpl findGroupByNickNameAndPassPhraseAndSize(String groupNickName, String groupPassPhrase, int groupSize) {
        return new GroupImpl(groupPersistenceService.findGroupByNickNameAndPassPhraseAndSize(groupNickName, groupPassPhrase, groupSize));
    }

    public GroupImpl updateGroup(long groupId, GroupImpl group) {
        if (group == null) {
            throw new CustomIllegalArgumentException("group must not be null.");
        }
        final GroupEntity updateGroupEntity;
        try {
            updateGroupEntity = groupPersistenceService.updateGroup(groupId, group.getGroupEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in updateGroup()");
        }
        return new GroupImpl(updateGroupEntity);
    }

    public GroupImpl persistGroup(final GroupImpl group) {
        if (group == null) {
            throw new CustomIllegalArgumentException("group must not be null.");
        }
        final GroupEntity persistedGroupEntity;
        try {
            persistedGroupEntity = groupPersistenceService.persistGroup(group.getGroupEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistGroup()");
        }
        return new GroupImpl(persistedGroupEntity);
    }

    public void deleteGroup(final GroupImpl group) {
        if (group == null) {
            throw new CustomIllegalArgumentException("group must not be null!");
        }
        groupPersistenceService.deleteGroup(group.getGroupEntity());
    }

    public GroupImpl createGroupEntity() {
        return new GroupImpl(groupPersistenceService.createGroupEntity());
    }

    public GroupImpl createGroupEntity(long id) {
        return new GroupImpl(groupPersistenceService.createGroupEntity(id));
    }


}
