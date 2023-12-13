package de.kleemann.co2_hsharz.core.group.emission;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.group.GroupImpl;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;

import java.util.Date;

/**
 * Class "GroupEmissionImpl" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupEmissionImpl implements GroupEmission {

    private final GroupEmissionEntity groupEmissionEntity;

    public GroupEmissionImpl(GroupEmissionEntity groupEmissionEntity) {
        if(groupEmissionEntity == null) {
            throw new CustomIllegalArgumentException("groupEmissionEntity must not be null.");
        }
        this.groupEmissionEntity = groupEmissionEntity;
    }

    @Override
    public long getGroupEmissionId() {
        return groupEmissionEntity.getGroupEmissionId();
    }

    @Override
    public void setGroupEmissionId(long groupEmissionId) {
        groupEmissionEntity.setGroupEmissionId(groupEmissionId);
    }

    @Override
    public String getGroupEmissionStartLocation() {
        return groupEmissionEntity.getGroupEmissionStartLocation();
    }

    @Override
    public void setGroupEmissionStartLocation(String groupEmissionStartLocation) {
        groupEmissionEntity.setGroupEmissionStartLocation(groupEmissionStartLocation);
    }

    @Override
    public String getGroupEmissionEndLocation() {
       return groupEmissionEntity.getGroupEmissionEndLocation();
    }

    @Override
    public void setGroupEmissionEndLocation(String groupEmissionEndLocation) {
        groupEmissionEntity.setGroupEmissionEndLocation(groupEmissionEndLocation);
    }

    @Override
    public boolean isGroupEmissionCustomTransportMedium() {
        return groupEmissionEntity.isGroupEmissionCustomTransportMedium();
    }

    @Override
    public void setGroupEmissionCustomTransportMedium(boolean groupEmissionCustomTransportMedium) {
        groupEmissionEntity.setGroupEmissionCustomTransportMedium(groupEmissionCustomTransportMedium);
    }

    @Override
    public TransportMediumEntity getGroupEmissionTransportMedium() {
        return groupEmissionEntity.getGroupEmissionTransportMedium();
    }

    @Override
    public void setGroupEmissionTransportMedium(TransportMediumEntity groupEmissionTransportMedium) {
        groupEmissionEntity.setGroupEmissionTransportMedium(groupEmissionTransportMedium);
    }

    @Override
    public GroupEntity getGroup() {
        return groupEmissionEntity.getGroup();
    }

    @Override
    public void setGroup(GroupEntity group) {
        groupEmissionEntity.setGroup(group);
    }

    @Override
    public double getGroupEmission() {
        return groupEmissionEntity.getGroupEmission();
    }

    @Override
    public void setGroupEmission(double groupEmission) {
        groupEmissionEntity.setGroupEmission(groupEmission);
    }

    @Override
    public double getGroupEmissionScore() {
        return groupEmissionEntity.getGroupEmissionScore();
    }

    @Override
    public void setGroupEmissionScore(double groupEmissionScore) {
        groupEmissionEntity.setGroupEmissionScore(groupEmissionScore);
    }

    @Override
    public Date getGroupEmissionCreateDate() {
        return groupEmissionEntity.getGroupEmissionCreateDate();
    }

    @Override
    public void setGroupEmissionCreateDate(Date groupEmissionCreateDate) {
        groupEmissionEntity.setGroupEmissionCreateDate(groupEmissionCreateDate);
    }

    public GroupEmissionEntity getGroupEmissionEntity() {
        return groupEmissionEntity;
    }
}
