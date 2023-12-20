package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;
import de.kleemann.co2_hsharz.core.group.emission.GroupEmissionImpl;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;

import java.util.Date;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController} API Endpoint
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

    //private GroupEntity group;
    private long groupId;
    private double groupEmission;
    private double groupEmissionScore;
    private Date groupEmissionCreateDate;


    /**
     * Constructs new {@link GroupEmissionResponseDTO} from {@link GroupEmissionImpl}
     * @param groupEmission - {@link GroupEmissionImpl} to convert
     */
    public GroupEmissionResponseDTO(GroupEmissionImpl groupEmission) {
        this(groupEmission.getGroupEmissionId(), groupEmission.getGroupEmissionStartLocation(),
                groupEmission.getGroupEmissionEndLocation(), groupEmission.isGroupEmissionCustomTransportMedium(),
                groupEmission.getGroupEmissionTransportMedium().getTransportMediumId(), groupEmission.getGroup().getGroupId(),
                groupEmission.getGroupEmission(), groupEmission.getGroupEmissionScore(),
                groupEmission.getGroupEmissionCreateDate());
    }

    /**
     * Required Args Constructor
     * 
     * @param groupEmissionId - {@code long} Id of this GroupEmission
     * @param groupEmissionStartLocation - {@link String} Name of the starting location
     * @param groupEmissionEndLocation - {@link String} Name of the ending location
     * @param groupEmissionCustomTransportMedium - {@boolean long} Flag if TransportMedium contains custom consumption
     * @param groupEmissionTransportMediumId - {@code long} Id of the transport medium
     * @param groupId - {@code long} Id of the group
     * @param groupEmission - {@code double} Amount of CO2 emitted
     * @param groupEmissionScore - {@code double} Score gained from this journey
     * @param groupEmissionCreateDate - {@link Date} Date of saving
     */
    public GroupEmissionResponseDTO(long groupEmissionId, String groupEmissionStartLocation,
                                    String groupEmissionEndLocation, boolean groupEmissionCustomTransportMedium,
                                    long groupEmissionTransportMediumId, long groupId, double groupEmission,
                                    double groupEmissionScore, Date groupEmissionCreateDate) {
        this.groupEmissionId = groupEmissionId;
        this.groupEmissionStartLocation = groupEmissionStartLocation;
        this.groupEmissionEndLocation = groupEmissionEndLocation;
        this.groupEmissionCustomTransportMedium = groupEmissionCustomTransportMedium;
        this.groupEmissionTransportMediumId = groupEmissionTransportMediumId;
        this.groupId = groupId;
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

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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
