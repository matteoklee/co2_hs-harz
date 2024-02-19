package de.kleemann.co2_hsharz.core.group.emission;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "GroupEmissionService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Service
public class GroupEmissionService {

    private final GroupEmissionPersistenceService groupEmissionPersistenceService;

    public GroupEmissionService(final GroupEmissionPersistenceService groupEmissionPersistenceService) {
        this.groupEmissionPersistenceService = groupEmissionPersistenceService;
    }

    public List<GroupEmissionImpl> findAllGroupEmissions() {
        return groupEmissionPersistenceService.findAllGroupEmissions()
                .stream()
                .map(GroupEmissionImpl::new)
                .collect(Collectors.toList());
    }

    public GroupEmissionImpl findGroupEmissionById(long groupEmissionId) {
        try {
            return new GroupEmissionImpl(groupEmissionPersistenceService.findGroupEmissionById(groupEmissionId));
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in findGroupEmissionById()");
        }
    }

    public GroupEmissionImpl updateGroupEmission(long groupEmissionId, GroupEmissionImpl groupEmission) {
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

    public GroupEmissionImpl persistGroupEmission(final GroupEmissionImpl groupEmission) {
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

    public void deleteGroupEmission(final GroupEmissionImpl groupEmission) {
        if (groupEmission == null) {
            throw new CustomIllegalArgumentException("groupEmission must not be null!");
        }
        groupEmissionPersistenceService.deleteGroupEmission(groupEmission.getGroupEmissionEntity());
    }

    public GroupEmissionImpl createGroupEmissionEntity() {
        return new GroupEmissionImpl(groupEmissionPersistenceService.createGroupEmissionEntity());
    }

    public GroupEmissionImpl createGroupEmissionEntity(long id) {
        return new GroupEmissionImpl(groupEmissionPersistenceService.createGroupEmissionEntity(id));
    }


}
