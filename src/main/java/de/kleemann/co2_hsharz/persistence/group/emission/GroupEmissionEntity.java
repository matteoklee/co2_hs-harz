package de.kleemann.co2_hsharz.persistence.group.emission;

import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Class "GroupEmissionEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Entity
public class GroupEmissionEntity {

    @Id
    @GeneratedValue
    private long groupEmissionId;
    private String groupEmissionStartLocation;
    private String groupEmissionEndLocation;
    private boolean groupEmissionCustomTransportMedium;
    private long groupEmissionTransportMediumId;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private GroupEntity group;
    private double groupEmission;
    private double groupEmissionScore;
    private Date groupEmissionCreateDate;


    public GroupEmissionEntity() {

    }

    public GroupEmissionEntity(long id) {
        setGroupEmissionId(id);
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
