package de.kleemann.co2_hsharz.api.group.dto;

import de.kleemann.co2_hsharz.api.group.GroupEmissionController;

/**
 * This Class is a Data Transfer Object for the {@link GroupEmissionController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public class GroupEmissionDTO {

    private double groupEmission;
    private String groupEmissionNickName;
    private String groupEmissionPassPhrase;
    private int groupEmissionSize;

    /**
     * Default Constructor. Use Setters to insert values.
     */
    public GroupEmissionDTO() {}

    /**
     * Required Args Constructor
     * 
     * @param groupEmission - {@code double} Amount of CO2 emitted
     * @param groupEmissionNickName - {@link String} Name
     * @param groupEmissionPassPhrase - {@link String} Passphrase
     * @param groupEmissionSize - {@code int} Amount of persons in the group
     */
    public GroupEmissionDTO(double groupEmission, String groupEmissionNickName, String groupEmissionPassPhrase, int groupEmissionSize) {
        this.groupEmission = groupEmission;
        this.groupEmissionNickName = groupEmissionNickName;
        this.groupEmissionPassPhrase = groupEmissionPassPhrase;
        this.groupEmissionSize = groupEmissionSize;
    }

    /**
     * Getter for {@code GroupEmissionDTO#groupEmission}
     * @return {@code double} CO2 Emitted
     */
    public double getGroupEmission() {
        return groupEmission;
    }

    /**
     * Setter for {@code GroupEmissionDTO#groupEmission}
     * @param groupEmission - New {@code double} groupEmission (CO2 Emitted by this group)
     */
    public void setGroupEmission(double groupEmission) {
        this.groupEmission = groupEmission;
    }

    /**
     * Getter for {@code GroupEmissionDTO#groupEmissionNickName}
     * @return {@link String} Nickname of this Group
     */
    public String getGroupEmissionNickName() {
        return groupEmissionNickName;
    }

    /**
     * Setter for {@code GroupEmissionDTO#groupEmissionNickName}
     * @param groupEmissionNickName - New {@link String} nickname of this group
     */
    public void setGroupEmissionNickName(String groupEmissionNickName) {
        this.groupEmissionNickName = groupEmissionNickName;
    }

    /**
     * Getter for {@code GroupEmissionDTO#groupEmissionPassPhrase}
     * @return {@link String} Passphrase of this Group
     */
    public String getGroupEmissionPassPhrase() {
        return groupEmissionPassPhrase;
    }

    /**
     * Setter for {@code GroupEmissionDTO#groupEmissionPassPhrase}
     * @param groupEmissionPassPhrase - New {@link String} passphrase of this group
     */
    public void setGroupEmissionPassPhrase(String groupEmissionPassPhrase) {
        this.groupEmissionPassPhrase = groupEmissionPassPhrase;
    }

    /**
     * Getter for {@code GroupEmissionDTO#groupEmissionSize}
     * @return {@code int} Amount of persons in this Group
     */
    public int getGroupEmissionSize() {
        return groupEmissionSize;
    }

    /**
     * Setter for {@code GroupEmissionDTO#groupEmissionSize}
     * @param groupEmissionSize - New {@code int} size of this group
     */
    public void setGroupEmissionSize(int groupEmissionSize) {
        this.groupEmissionSize = groupEmissionSize;
    }
}
