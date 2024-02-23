package de.kleemann.co2_hsharz.core.group;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.group.GroupPersistenceService;
import lombok.NonNull;

/**
 * This Service provides core layer functionality to create, read, update and delete {@link Group}s
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupService {

	/**
	 * {@link GroupPersistenceService}
	 */
    private final GroupPersistenceService groupPersistenceService;

    /**
     * Constructs a {@link GroupService} using a {@link GroupPersistenceService}
     * @param groupPersistenceService - {@link GroupPersistenceService}
     */
    public GroupService(final GroupPersistenceService groupPersistenceService) {
        this.groupPersistenceService = groupPersistenceService;
    }

    /**
     * Finds all {@link Group}s in the database
     * @return {@link List} of all {@link GroupImpl}
     */
    public List<GroupImpl> findAllGroups() {
        return groupPersistenceService.findAllGroups()
                .stream()
                .map(GroupImpl::new)
                .collect(Collectors.toList());
    }

    /**
     * Finds a single {@link Group} by Id
     * @param groupId - {@code long} Id
     * @return {@link GroupImpl} with this Id
     * @throws CustomRuntimeException if no {@link Group} with this Id was found or an error occurred
     */
    public GroupImpl findGroupById(long groupId) {
        try {
            return new GroupImpl(groupPersistenceService.findGroupById(groupId));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findGroupById()");
        }
    }

    /**
     * Finds a single group by nickname, passphrase and size
     * @param groupNickName - {@link String} NickName of the {@link Group}
     * @param groupPassPhrase - {@link String} PassPhrase of the {@link Group}
     * @param groupSize - {@code int} Size of this {@link Group}
     * @return {@link GroupImpl} if a {@link Group} with this properties has been found
     * @throws CustomIllegalArgumentException if no Group has been found
     */
    public GroupImpl findGroupByNickNameAndPassPhraseAndSize(String groupNickName, String groupPassPhrase, int groupSize) {
        return new GroupImpl(groupPersistenceService.findGroupByNickNameAndPassPhraseAndSize(groupNickName, groupPassPhrase, groupSize));
    }

    /**
     * Updates a {@link Group}
     * @param groupId - {@code long} Id of the {@link Group}
     * @param group - {@link GroupImpl} changed {@link Group}
     * @return Updated {@link GroupImpl}
     * @throws CustomIllegalArgumentException if group is null or <br>
     * {@link CustomRuntimeException} if {@link Group} has not been found in database or an error occurred while updating
     */
    public GroupImpl updateGroup(long groupId, @NonNull GroupImpl group) {
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

    /**
     * Persist a {@link Group}
     * @param group - {@link GroupImpl} Group to persist
     * @return {@link GroupImpl} persisted Group
     * @throws {@link CustomIllegalArgumentException} if group is null or
     * <br>		{@link CustomRuntimeException} if an error occurred
     */
    public GroupImpl persistGroup(@NonNull final GroupImpl group) {
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

    /**
     * Deletes a {@link Group}
     * @param group - {@link GroupImpl} to delete
     * @throws CustomIllegalArgumentException if group is null
     */
    public void deleteGroup(@NonNull final GroupImpl group) {
        if (group == null) {
            throw new CustomIllegalArgumentException("group must not be null!");
        }
        groupPersistenceService.deleteGroup(group.getGroupEntity());
    }

    /**
     * Creates a new {@link GroupEntity} and maps it to a {@link GroupImpl}
     * @return {@link GroupImpl} of the created {@link GroupEntity}
     */
    public GroupImpl createGroupEntity() {
        return new GroupImpl(groupPersistenceService.createGroupEntity());
    }

    /**
     * Creates a new {@link GroupEntity} with the given id and maps it to a {@link GroupImpl}
     * @param id {@link Long} Id for the {@link GroupEntity} 
     * @return {@link GroupImpl} of the created {@link GroupEntity}
     */
    public GroupImpl createGroupEntity(long id) {
        return new GroupImpl(groupPersistenceService.createGroupEntity(id));
    }


}
