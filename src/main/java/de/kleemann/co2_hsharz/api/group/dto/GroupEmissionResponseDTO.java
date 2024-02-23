package de.kleemann.co2_hsharz.api.group.dto;

import java.util.Date;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmissionImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.12.2023
 */
@Data
@AllArgsConstructor
public class GroupEmissionResponseDTO {

    private long groupEmissionId;
    private String groupEmissionStartLocation;
    private String groupEmissionEndLocation;
    private boolean groupEmissionCustomTransportMedium;
    private long groupEmissionTransportMediumId;

    //private GroupEntity group;
    private long groupId;
    private double groupEmission;
    private double groupEmissionScore;
    private Date groupEmissionCreateDate;


    /**
     * Constructs new {@link GroupEmissionResponseDTO} from {@link GroupEmissionImpl}
     * @param groupEmission - {@link GroupEmissionImpl} to convert
     */
    public GroupEmissionResponseDTO(GroupEmissionImpl groupEmission) {
        this(groupEmission.getGroupEmissionId(), groupEmission.getGroupEmissionStartLocation(),
                groupEmission.getGroupEmissionEndLocation(), groupEmission.isGroupEmissionCustomTransportMedium(),
                groupEmission.getGroupEmissionTransportMedium().getTransportMediumId(), groupEmission.getGroup().getGroupId(),
                groupEmission.getGroupEmission(), groupEmission.getGroupEmissionScore(),
                groupEmission.getGroupEmissionCreateDate());
    }
}
