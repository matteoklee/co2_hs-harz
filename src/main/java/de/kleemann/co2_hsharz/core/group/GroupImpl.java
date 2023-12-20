package de.kleemann.co2_hsharz.core.group;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import lombok.NonNull;

/**
 * This class is the Implementation of a {@link Group}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupImpl implements Group{

	/**
	 * {@link GroupEntity}, from which this {@link GroupImpl} was constructed
	 */
    private final GroupEntity groupEntity;

    /**
     * Constructs a new {@link GroupImpl} from a {@link GroupEntity}
     * @param groupEntity - {@link GroupEntity}
     * @throws CustomIllegalArgumentException if groupEntity is null
     */
    public GroupImpl(@NonNull GroupEntity groupEntity) {
        if(groupEntity == null) {
            throw new CustomIllegalArgumentException("groupEntity must not be null.");
        }
        this.groupEntity = groupEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getGroupId() {
        return groupEntity.getGroupId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupId(long groupId) {
        groupEntity.setGroupId(groupId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGroupNickName() {
        return groupEntity.getGroupNickName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupNickName(String groupNickName) {
        groupEntity.setGroupNickName(groupNickName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGroupPassPhrase() {
        return groupEntity.getGroupPassPhrase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupPassPhrase(String groupPassPhrase) {
        groupEntity.setGroupPassPhrase(groupPassPhrase);
    }

    @Override
    public int getGroupSize() {
        return groupEntity.getGroupSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupSize(int groupSize) {
        groupEntity.setGroupSize(groupSize);
    }

    /**
     * Returns the {@link GroupEntity} this {@link GroupImpl} was constructed from
     * @return {@link GroupEntity}
     */
    public GroupEntity getGroupEntity() {
        return groupEntity;
    }
}
