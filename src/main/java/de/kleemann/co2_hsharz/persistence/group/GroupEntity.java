package de.kleemann.co2_hsharz.persistence.group;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Entity represents a travel group, which emits co2 on its journey. <br>
 * A group has an id, a nickname, a passphrase and a count of members (size)
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Entity
@Data
@NoArgsConstructor
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;
    @Column(unique = true, nullable = false)
    private String groupNickName;
    @Column(nullable = false)
    private String groupPassPhrase;
    private int groupSize;

    public GroupEntity(long id) {
        setGroupId(id);
    }
}
