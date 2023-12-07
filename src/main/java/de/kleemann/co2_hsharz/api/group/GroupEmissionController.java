package de.kleemann.co2_hsharz.api.group;

import de.kleemann.co2_hsharz.api.group.dto.GroupEmissionDTO;
import de.kleemann.co2_hsharz.api.group.dto.GroupEmissionRequestDTO;
import de.kleemann.co2_hsharz.api.group.dto.GroupEmissionResponseDTO;
import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.group.GroupImpl;
import de.kleemann.co2_hsharz.core.group.GroupService;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmission;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmissionImpl;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmissionService;
import de.kleemann.co2_hsharz.core.transport.TransportMediumImpl;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "GroupEmissionController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */

@RestController
@RequestMapping("/api")
public class GroupEmissionController {

    private final GroupEmissionService groupEmissionService;
    private final TransportMediumService transportMediumService;
    private final GroupService groupService;

    public GroupEmissionController(GroupEmissionService groupEmissionService, TransportMediumService transportMediumService,
                                   GroupService groupService){
        this.groupEmissionService = groupEmissionService;
        this.transportMediumService = transportMediumService;
        this.groupService = groupService;
    }

    /**
     * Saves a GroupEmission to database
     *
     * @param groupEmissionRequestDTO
     */
    @PostMapping("/groupEmission")
    public ResponseEntity<GroupEmissionResponseDTO> saveGroupEmission(@RequestBody GroupEmissionRequestDTO groupEmissionRequestDTO){
        GroupEmissionDTO groupEmissionDTO = groupEmissionRequestDTO.getGroupEmissionDTO();
        TransportMediumDTO transportMediumDTO = groupEmissionRequestDTO.getTransportMediumDTO();


        GroupEmissionImpl groupEmission = groupEmissionService.createGroupEmissionEntity();
        groupEmission.setGroupEmissionStartLocation(groupEmissionRequestDTO.getStartLocation());
        groupEmission.setGroupEmissionEndLocation(groupEmissionRequestDTO.getEndLocation());

        if(transportMediumDTO.getTransportMediumFuelConsumption() != null) {
            groupEmission.setGroupEmissionCustomTransportMedium(true);
            TransportMediumImpl defaultTransportMedium = transportMediumService
                    .findTransportMediumByNameAndSizeAndFuel(TransportMediumName.CAR,
                            TransportMediumSize.MEDIUM,
                            TransportMediumFuel.PETROL);
            groupEmission.setGroupEmissionTransportMediumId(defaultTransportMedium.getTransportMediumId());
        } else {
            groupEmission.setGroupEmissionCustomTransportMedium(false);
            TransportMediumImpl transportMedium = transportMediumService.findTransportMediumByCustomInput(transportMediumDTO);
            groupEmission.setGroupEmissionTransportMediumId(transportMedium.getTransportMediumId());
        }

        String groupNickName = groupEmissionDTO.getGroupEmissionNickName();
        String groupPassPhrase = groupEmissionDTO.getGroupEmissionPassPhrase();
        int groupSize = groupEmissionDTO.getGroupEmissionSize();
        GroupImpl existingGroup = null;

        try {
            existingGroup = groupService.findGroupByNickNameAndPassPhraseAndSize(groupNickName, groupPassPhrase, groupSize);
        } catch (Exception exception) {
            //
        }
        if(existingGroup == null) {
            GroupImpl group = groupService.createGroupEntity();
            group.setGroupNickName(groupNickName);
            group.setGroupPassPhrase(groupPassPhrase);
            group.setGroupSize(groupSize);
            final GroupImpl persistedGroup = groupService.persistGroup(group);
            existingGroup = persistedGroup;
        }

        groupEmission.setGroup(existingGroup.getGroupEntity());
        groupEmission.setGroupEmission(groupEmissionDTO.getGroupEmission());
        groupEmission.setGroupEmissionCreateDate(new Date());

        //TODO: calculate Score
        groupEmission.setGroupEmissionScore(0.0);

         /*
            {
                "startLocation": "Wernigerode",
                "endLocation": "Magdeburg",
                "transportMediumDTO": {
                    "transportMediumName": "train",
                    "transportMediumSize": "",
                    "transportMediumFuel": "diesel",
                    "transportMediumFuelConsumption": 7
                },
                "groupEmissionDTO": {
                    "groupEmission": 15.3,
                    "groupEmissionNickName": "18777",
                    "groupEmissionPassPhrase": "Niko",
                    "groupEmissionSize": 3
                }
            }
          */


        //set attributes
        final GroupEmissionImpl persistedGroupEmission = groupEmissionService.persistGroupEmission(groupEmission);
        return ResponseEntity.ok(new GroupEmissionResponseDTO(persistedGroupEmission));
    }

    /**
     * Returns a history of the groups emissions
     * @return History of emissions
     */
    @GetMapping("/groupEmission")
    public List<GroupEmissionResponseDTO> getGroupEmissions() {
        return new ArrayList<>(groupEmissionService.findAllGroupEmissions()
                .stream()
                .map(GroupEmissionResponseDTO::new)
                .collect(Collectors.toList()));
    }
}
