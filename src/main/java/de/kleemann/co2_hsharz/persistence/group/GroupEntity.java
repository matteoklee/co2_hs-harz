package de.kleemann.co2_hsharz.persistence.group;

import de.kleemann.co2_hsharz.persistence.group.emission.GroupEmissionEntity;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Class "GroupEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Entity
public class GroupEntity {

    @Id
    @GeneratedValue
    private long groupId;
    @Column(unique = true, nullable = false)
    private String groupNickName;
    @Column(nullable = false)
    private String groupPassPhrase;
    private int groupSize;

    public GroupEntity() {
    }

    public GroupEntity(long id) {
        setGroupId(id);
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

    public String getGroupPassPhrase() {
        return groupPassPhrase;
    }

    public void setGroupPassPhrase(String groupPassPhrase) {
        this.groupPassPhrase = groupPassPhrase;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

}
