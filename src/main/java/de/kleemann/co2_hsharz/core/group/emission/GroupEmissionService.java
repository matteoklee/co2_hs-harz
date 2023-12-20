package de.kleemann.co2_hsharz.core.group.emission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionPersistenceService;
import lombok.NonNull;

/**
 * This Service provides core layer functionality to create, read, update and delete {@link GroupEmission}s
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupEmissionService {

	/**
	 * {@link GroupEmissionPersistenceService}
	 */
    private final GroupEmissionPersistenceService groupEmissionPersistenceService;

    /**
     * Constructs a {@link GroupEmissionService} using a {@link GroupEmissionPersistenceService}
     * @param groupEmissionPersistenceService - {@link GroupEmissionPersistenceService}
     */
    public GroupEmissionService(final GroupEmissionPersistenceService groupEmissionPersistenceService) {
        this.groupEmissionPersistenceService = groupEmissionPersistenceService;
    }

    /**
     * Returns a {@link List} of all {@link GroupEmissionImpl} found in database
     * @return {@link List} of {@link GroupEmissionImpl}
     */
    public List<GroupEmissionImpl> findAllGroupEmissions() {
        return groupEmissionPersistenceService.findAllGroupEmissions()
                .stream()
                .map(GroupEmissionImpl::new)
                .collect(Collectors.toList());
    }

    /**
     * Attempts to find a single {@link GroupEmission} by its Id
     * @param groupEmissionId - {@code long} Id
     * @return {@link GroupEmissionImpl} with this Id, if found
     * @throws CustomRuntimeException, if no Entity was found or an error occurred
     */
    public GroupEmissionImpl findGroupEmissionById(long groupEmissionId) {
        try {
            return new GroupEmissionImpl(groupEmissionPersistenceService.findGroupEmissionById(groupEmissionId));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findGroupEmissionById()");
        }
    }

    /**
     * Attempts to update a {@link GroupEmission}, found by Id
     * @param groupEmissionId - {@code long} Id
     * @param groupEmission - Changed {@link GroupEmissionImpl}
     * @return Updated {@link GroupEmissionImpl}
     * @throws CustomRuntimeException if no Entity was found or an error occurred
     * @throws CustomIllegalArgumentException if groupEmission is null
     */
    public GroupEmissionImpl updateGroupEmission(long groupEmissionId, @NonNull GroupEmissionImpl groupEmission) {
        if (groupEmission == null) {
            throw new CustomIllegalArgumentException("groupEmission must not be null.");
        }
        final GroupEmissionEntity updateGroupEmissionEntity;
        try {
            updateGroupEmissionEntity = groupEmissionPersistenceService.updateGroupEmission(groupEmissionId, groupEmission.getGroupEmissionEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in updateGroupEmission()");
        }
        return new GroupEmissionImpl(updateGroupEmissionEntity);
    }

    /**
     * Persists a {@link GroupEmission} in database
     * @param groupEmission - {@link GroupEmissionImpl} to persist
     * @return Persisted {@link GroupEmissionImpl}
     * @throws CustomRuntimeException if an error occurred
     * @throws CustomIllegalArgumentException if groupEmission is null
     */
    public GroupEmissionImpl persistGroupEmission(@NonNull final GroupEmissionImpl groupEmission) {
        if (groupEmission == null) {
            throw new CustomIllegalArgumentException("groupEmission must not be null.");
        }
        final GroupEmissionEntity persistedGroupEmissionEntity;
        try {
            persistedGroupEmissionEntity = groupEmissionPersistenceService.persistGroupEmission(groupEmission.getGroupEmissionEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistGroupEmission()");
        }
        return new GroupEmissionImpl(persistedGroupEmissionEntity);
    }

    /**
     * Deletes a {@link GroupEmission} from Database
     * @param groupEmission - {@link GroupEmissionImpl} to delete
     * @throws CustomIllegalArgumentException if groupEmission is null
     */
    public void deleteGroupEmission(@NonNull final GroupEmissionImpl groupEmission) {
        if (groupEmission == null) {
            throw new CustomIllegalArgumentException("groupEmission must not be null!");
        }
        groupEmissionPersistenceService.deleteGroupEmission(groupEmission.getGroupEmissionEntity());
    }

    /**
     * Creates a new {@link GroupEmission}
     * @return new {@link GroupEmissionImpl}
     */
    public GroupEmissionImpl createGroupEmissionEntity() {
        return new GroupEmissionImpl(groupEmissionPersistenceService.createGroupEmissionEntity());
    }

    /**
     * Creates a new {@link GroupEmission} with a specific id
     * @param id - {@code id} 
     * @return new {@link GroupEmissionImpl} with this id
     */
    public GroupEmissionImpl createGroupEmissionEntity(long id) {
        return new GroupEmissionImpl(groupEmissionPersistenceService.createGroupEmissionEntity(id));
    }
}
