package de.kleemann.co2_hsharz.api.group;

import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class "GroupController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@RestController
@RequestMapping("/api/group")
public class GroupController {

    /**
     * Returns Information about a group
     * (does not contain the passphrase)
     *
     * @param groupNickName
     * @param groupPassPhrase
     * @return group information
     */
    @GetMapping
    public GroupEntity getGroupInformation(@PathVariable String groupNickName, @PathVariable String groupPassPhrase){
        return null;
    }

}
