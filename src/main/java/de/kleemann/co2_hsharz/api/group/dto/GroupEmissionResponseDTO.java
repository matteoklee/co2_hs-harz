package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.core.group.emission.GroupEmissionImpl;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;

import java.util.Date;

/**
 * Class "GroupEmissionResponseDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 07.12.2023
 */
public class GroupEmissionResponseDTO {

    private long groupEmissionId;
    private String groupEmissionStartLocation;
    private String groupEmissionEndLocation;
    private boolean groupEmissionCustomTransportMedium;
    private long groupEmissionTransportMediumId;

    private GroupEntity group;
    private double groupEmission;
    private double groupEmissionScore;
    private Date groupEmissionCreateDate;


    public GroupEmissionResponseDTO(GroupEmissionImpl groupEmission) {
        this(groupEmission.getGroupEmissionId(), groupEmission.getGroupEmissionStartLocation(),
                groupEmission.getGroupEmissionEndLocation(), groupEmission.isGroupEmissionCustomTransportMedium(),
                groupEmission.getGroupEmissionTransportMediumId(), groupEmission.getGroup(),
                groupEmission.getGroupEmission(), groupEmission.getGroupEmissionScore(),
                groupEmission.getGroupEmissionCreateDate());
    }

    public GroupEmissionResponseDTO(long groupEmissionId, String groupEmissionStartLocation,
                                    String groupEmissionEndLocation, boolean groupEmissionCustomTransportMedium,
                                    long groupEmissionTransportMediumId, GroupEntity group, double groupEmission,
                                    double groupEmissionScore, Date groupEmissionCreateDate) {
        this.groupEmissionId = groupEmissionId;
        this.groupEmissionStartLocation = groupEmissionStartLocation;
        this.groupEmissionEndLocation = groupEmissionEndLocation;
        this.groupEmissionCustomTransportMedium = groupEmissionCustomTransportMedium;
        this.groupEmissionTransportMediumId = groupEmissionTransportMediumId;
        this.group = group;
        this.groupEmission = groupEmission;
        this.groupEmissionScore = groupEmissionScore;
        this.groupEmissionCreateDate = groupEmissionCreateDate;
    }

    public long getGroupEmissionId() {
        return groupEmissionId;
    }

    public void setGroupEmissionId(long groupEmissionId) {
        this.groupEmissionId = groupEmissionId;
    }

    public String getGroupEmissionStartLocation() {
        return groupEmissionStartLocation;
    }

    public void setGroupEmissionStartLocation(String groupEmissionStartLocation) {
        this.groupEmissionStartLocation = groupEmissionStartLocation;
    }

    public String getGroupEmissionEndLocation() {
        return groupEmissionEndLocation;
    }

    public void setGroupEmissionEndLocation(String groupEmissionEndLocation) {
        this.groupEmissionEndLocation = groupEmissionEndLocation;
    }

    public boolean isGroupEmissionCustomTransportMedium() {
        return groupEmissionCustomTransportMedium;
    }

    public void setGroupEmissionCustomTransportMedium(boolean groupEmissionCustomTransportMedium) {
        this.groupEmissionCustomTransportMedium = groupEmissionCustomTransportMedium;
    }

    public long getGroupEmissionTransportMediumId() {
        return groupEmissionTransportMediumId;
    }

    public void setGroupEmissionTransportMediumId(long groupEmissionTransportMediumId) {
        this.groupEmissionTransportMediumId = groupEmissionTransportMediumId;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public double getGroupEmission() {
        return groupEmission;
    }

    public void setGroupEmission(double groupEmission) {
        this.groupEmission = groupEmission;
    }

    public double getGroupEmissionScore() {
        return groupEmissionScore;
    }

    public void setGroupEmissionScore(double groupEmissionScore) {
        this.groupEmissionScore = groupEmissionScore;
    }

    public Date getGroupEmissionCreateDate() {
        return groupEmissionCreateDate;
    }

    public void setGroupEmissionCreateDate(Date groupEmissionCreateDate) {
        this.groupEmissionCreateDate = groupEmissionCreateDate;
    }
}
