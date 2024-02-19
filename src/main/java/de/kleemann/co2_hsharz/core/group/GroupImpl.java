package de.kleemann.co2_hsharz.core.group;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;

/**
 * Class "GroupImpl" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupImpl implements Group {

    private final GroupEntity groupEntity;

    public GroupImpl(GroupEntity groupEntity) {
        if(groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null.");
        }
        this.groupEntity = groupEntity;
    }

    @Override
    public long getGroupId() {
        return groupEntity.getGroupId();
    }

    @Override
    public void setGroupId(long groupId) {
        groupEntity.setGroupId(groupId);
    }

    @Override
    public String getGroupNickName() {
        return groupEntity.getGroupNickName();
    }

    @Override
    public void setGroupNickName(String groupNickName) {
        groupEntity.setGroupNickName(groupNickName);
    }

    @Override
    public String getGroupPassPhrase() {
        return groupEntity.getGroupPassPhrase();
    }

    @Override
    public void setGroupPassPhrase(String groupPassPhrase) {
        groupEntity.setGroupPassPhrase(groupPassPhrase);
    }

    @Override
    public int getGroupSize() {
        return groupEntity.getGroupSize();
    }

    @Override
    public void setGroupSize(int groupSize) {
        groupEntity.setGroupSize(groupSize);
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }
}
