package de.kleemann.co2_hsharz.core.group.emission;

import java.util.Date;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import lombok.NonNull;

/**
 * This class is the Implementation of {@link GroupEmission}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupEmissionImpl implements GroupEmission {

	/**
	 * {@link GroupEmissionEntity} used to construct this {@link GroupEmissionImpl}
	 */
    private final GroupEmissionEntity groupEmissionEntity;

    /**
     * Constructs a new {@link GroupEmissionImpl} from a {@link GroupEmissionEntity}
     * @param groupEmissionEntity - {@link GroupEmissionEntity}
     */
    public GroupEmissionImpl(@NonNull GroupEmissionEntity groupEmissionEntity) {
        if(groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null.");
        }
        this.groupEmissionEntity = groupEmissionEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getGroupEmissionId() {
        return groupEmissionEntity.getGroupEmissionId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionId(long groupEmissionId) {
        groupEmissionEntity.setGroupEmissionId(groupEmissionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGroupEmissionStartLocation() {
        return groupEmissionEntity.getGroupEmissionStartLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionStartLocation(String groupEmissionStartLocation) {
        groupEmissionEntity.setGroupEmissionStartLocation(groupEmissionStartLocation);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getGroupEmissionEndLocation() {
       return groupEmissionEntity.getGroupEmissionEndLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionEndLocation(String groupEmissionEndLocation) {
        groupEmissionEntity.setGroupEmissionEndLocation(groupEmissionEndLocation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGroupEmissionCustomTransportMedium() {
        return groupEmissionEntity.isGroupEmissionCustomTransportMedium();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionCustomTransportMedium(boolean groupEmissionCustomTransportMedium) {
        groupEmissionEntity.setGroupEmissionCustomTransportMedium(groupEmissionCustomTransportMedium);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransportMediumEntity getGroupEmissionTransportMedium() {
        return groupEmissionEntity.getGroupEmissionTransportMedium();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionTransportMedium(TransportMediumEntity groupEmissionTransportMedium) {
        groupEmissionEntity.setGroupEmissionTransportMedium(groupEmissionTransportMedium);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupEntity getGroup() {
        return groupEmissionEntity.getGroup();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroup(GroupEntity group) {
        groupEmissionEntity.setGroup(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGroupEmission() {
        return groupEmissionEntity.getGroupEmission();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmission(double groupEmission) {
        groupEmissionEntity.setGroupEmission(groupEmission);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getGroupEmissionScore() {
        return groupEmissionEntity.getGroupEmissionScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionScore(double groupEmissionScore) {
        groupEmissionEntity.setGroupEmissionScore(groupEmissionScore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getGroupEmissionCreateDate() {
        return groupEmissionEntity.getGroupEmissionCreateDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGroupEmissionCreateDate(Date groupEmissionCreateDate) {
        groupEmissionEntity.setGroupEmissionCreateDate(groupEmissionCreateDate);
    }

    /**
     * Returns the {@link GroupEmissionEntity} used to construct this {@link GroupEmissionImpl}
     * @return {@link GroupEmissionEntity} used to construct this object 
     */
    public GroupEmissionEntity getGroupEmissionEntity() {
        return groupEmissionEntity;
    }
}
