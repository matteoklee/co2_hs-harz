package de.kleemann.co2_hsharz.api.group.dto;

/**
 * Class "GroupEmissionDTO" is used for ...
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

    public GroupEmissionDTO() {}

    public GroupEmissionDTO(double groupEmission, String groupEmissionNickName, String groupEmissionPassPhrase, int groupEmissionSize) {
        this.groupEmission = groupEmission;
        this.groupEmissionNickName = groupEmissionNickName;
        this.groupEmissionPassPhrase = groupEmissionPassPhrase;
        this.groupEmissionSize = groupEmissionSize;
    }

    public double getGroupEmission() {
        return groupEmission;
    }

    public void setGroupEmission(double groupEmission) {
        this.groupEmission = groupEmission;
    }

    public String getGroupEmissionNickName() {
        return groupEmissionNickName;
    }

    public void setGroupEmissionNickName(String groupEmissionNickName) {
        this.groupEmissionNickName = groupEmissionNickName;
    }

    public String getGroupEmissionPassPhrase() {
        return groupEmissionPassPhrase;
    }

    public void setGroupEmissionPassPhrase(String groupEmissionPassPhrase) {
        this.groupEmissionPassPhrase = groupEmissionPassPhrase;
    }

    public int getGroupEmissionSize() {
        return groupEmissionSize;
    }

    public void setGroupEmissionSize(int groupEmissionSize) {
        this.groupEmissionSize = groupEmissionSize;
    }
}
