package de.kleemann.co2_hsharz.api.group;

import de.kleemann.co2_hsharz.api.group.dto.GroupResponseDTO;
import de.kleemann.co2_hsharz.core.group.GroupImpl;
import de.kleemann.co2_hsharz.core.group.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "GroupController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group")
    public List<GroupResponseDTO> getAllGroups() {
        return new ArrayList<>(groupService.findAllGroups()
                .stream()
                .map(GroupResponseDTO::new)
                .collect(Collectors.toList()));
    }

}
