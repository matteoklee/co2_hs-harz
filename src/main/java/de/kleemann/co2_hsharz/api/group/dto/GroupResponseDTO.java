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
//    private String groupPassPhrase;
    private int groupSize;

    public GroupResponseDTO(GroupImpl group) {
        this(group.getGroupId(), group.getGroupNickName(), group.getGroupPassPhrase(), group.getGroupSize());
    }

    public GroupResponseDTO(long groupId, String groupNickName, String groupPassPhrase, int groupSize) {
        this.groupId = groupId;
        this.groupNickName = groupNickName;
//        this.groupPassPhrase = groupPassPhrase;
        this.groupSize = groupSize;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupNickName() {
        return groupNickName;
    }

    public void setGroupNickName(String groupNickName) {
        this.groupNickName = groupNickName;
    }

//    public String getGroupPassPhrase() {
//        return groupPassPhrase;
//    }

//    public void setGroupPassPhrase(String groupPassPhrase) {
//        this.groupPassPhrase = groupPassPhrase;
//    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
