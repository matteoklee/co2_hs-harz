package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.core.group.GroupImpl;

/**
 * Class "GroupResponseDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.12.2023
 */
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

    /**
     * Required Args Constructor
     * 
     * @param groupId - {@code long} Id of the group
     * @param groupNickName - {@link String} Nickname
     * @param groupSize - {@code int} amount of persons in this group
     */
    public GroupResponseDTO(long groupId, String groupNickName, int groupSize) {
        this.groupId = groupId;
        this.groupNickName = groupNickName;
        this.groupSize = groupSize;
    }

    /**
     * Getter for {@code GroupResponseDTO#groupId}
     * @return {@code long} Id of the group
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * Setter for {@code GroupResponseDTO#groupId}
     * @param groupId - New {@code long} groupId
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    /**
     * Getter for {@code GroupResponseDTO#groupNickName}
     * @return {@link String} Nickname of the group
     */
    public String getGroupNickName() {
        return groupNickName;
    }

    /**
     * Setter for {@code GroupResponseDTO#groupNickName}
     * @param groupNickName - New {@link String} groupNickName
     */
    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }

    /**
     * Getter for {@code GroupResponseDTO#groupSize}
     * @return {@code int} Amount of persons in the group
     */
    public int getGroupSize() {
        return groupSize;
    }

    /**
     * Setter for {@code GroupResponseDTO#groupSize}
     * @param groupSize - New {@code int} groupSize
     */
    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
