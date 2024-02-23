package de.kleemann.co2_hsharz.persistence.group.emission;

import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This Entity represents a single emission, emitted by a {@link GroupEntity}. <br>
 * It has an ID, a location for the start and destination of the journey, a transport medium, the emission value and score and the date of its creation
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Data
@Entity
@NoArgsConstructor
public class GroupEmissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupEmissionId;
    private String groupEmissionStartLocation;
    private String groupEmissionEndLocation;
    private boolean groupEmissionCustomTransportMedium;

    //private long groupEmissionTransportMediumId;

    @ManyToOne
    @JoinColumn(name = "transportMediumId")
    private TransportMediumEntity groupEmissionTransportMedium;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private GroupEntity group;
    private double groupEmission;
    private double groupEmissionScore;
    private Date groupEmissionCreateDate;

    public GroupEmissionEntity(long id) {
        setGroupEmissionId(id);
    }
}
