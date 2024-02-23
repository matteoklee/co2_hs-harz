package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;
import de.kleemann.co2_hsharz.core.group.GroupImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController#saveGroupEmission(GroupEmissionRequestDTO)} Response
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.12.2023
 */
@Data
@AllArgsConstructor
public class GroupResponseDTO {

    private long groupId;
    private String groupNickName;
    private int groupSize;

    /**
     * Constructs new {@link GroupResponseDTO} from {@link GroupImpl}
     * @param group - {@link GroupImpl}
     */
    public GroupResponseDTO(GroupImpl group) {
        this(group.getGroupId(), group.getGroupNickName(), group.getGroupSize());
    }
}
