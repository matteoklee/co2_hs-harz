package de.kleemann.co2_hsharz.api.group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.api.group.dto.GroupResponseDTO;
import de.kleemann.co2_hsharz.core.group.GroupService;

/**
 * This API Controller offers the API Endpoint {@code /group} which can be used to create and retrieve groups.
 * <br> See the API Documentation at <a href=https://github.com/matteoklee/co2_hs-harz/wiki/API-Endpunkte> Github </a> for detailed information
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    /**
     * Constructor with Auto-Wired required Services
     * 
     * @param groupService - {@link GroupService}
     */
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Returns a list of all existing groups
     * 
     * @return {@link List} of {@link GroupResponseDTO}
     */
    @GetMapping("/group")
    public List<GroupResponseDTO> getAllGroups() {
        return new ArrayList<>(groupService.findAllGroups()
                .stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList()));
    }

}
