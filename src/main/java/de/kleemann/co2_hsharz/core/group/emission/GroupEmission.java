package de.kleemann.co2_hsharz.core.group.emission;

import de.kleemann.co2_hsharz.core.group.Group;
import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.persistence.group.GroupEntity;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;

import java.util.Date;

/**
 * This Interface represents a GroupEmission on the core layer <br>
 * A groupEmission is a instance of co2 emitted on a journey (route and {@link TransportMedium}) by a {@link Group} <br>
 * It contains an Id, a route (startLocation, endLocation), {@link TransportMedium}, {@link Group}, Co2 Emission, Score and creation date
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public interface GroupEmission {

	/**
	 * Getter for the Id of this {@link GroupEmission}
	 * @return {@code long} Id of this {@link GroupEmission}
	 */
    public long getGroupEmissionId();

    /**
   	 * Setter for the Id of this {@link GroupEmission}
   	 * @param groupEmissionId - {@code long} New Id of this {@link GroupEmission}
   	 */
    public void setGroupEmissionId(long groupEmissionId);

    /**
	 * Getter for the StartLocation of this {@link GroupEmission}
	 * @return {@link String} StartLocation of this {@link GroupEmission}
	 */
    public String getGroupEmissionStartLocation();

    /**
   	 * Setter for the StartLocation of this {@link GroupEmission}
   	 * @param groupEmissionStartLocation - {@link String} New StartLocation of this {@link GroupEmission}
   	 */
    public void setGroupEmissionStartLocation(String groupEmissionStartLocation);

    /**
	 * Getter for the EndLocation of this {@link GroupEmission}
	 * @return {@link String} EndLocation of this {@link GroupEmission}
	 */
    public String getGroupEmissionEndLocation();

    /**
   	 * Setter for the EndLocation of this {@link GroupEmission}
   	 * @param groupEmissionEndLocation - {@link String} New EndLocation of this {@link GroupEmission}
   	 */
    public void setGroupEmissionEndLocation(String groupEmissionEndLocation);

    /**
	 * Getter for the CustomTransportMedium Flag of this {@link GroupEmission}
	 * @return {@code boolean} if the TransportMedium of this {@link GroupEmission} is custom
	 */
    public boolean isGroupEmissionCustomTransportMedium();

    /**
   	 * Setter for the CustomTransportMedium Flag of this {@link GroupEmission}
   	 * @param groupEmissionCustomTransportMedium - {@code boolean} if {@link GroupEmission} uses a custom transportmedium
   	 */
    public void setGroupEmissionCustomTransportMedium(boolean groupEmissionCustomTransportMedium);

    /**
	 * Getter for the groupEmissionTransportMedium of this {@link GroupEmission}
	 * @return {@link TransportMediumEntity} TransportMedium of this {@link GroupEmission}
	 */
    public TransportMediumEntity getGroupEmissionTransportMedium();

    /**
   	 * Setter for the TransportMedium of this {@link GroupEmission}
   	 * @param groupEmissionTransportMedium - {@link TransportMediumEntity} New TransportMedium of this {@link GroupEmission}
   	 */
    public void setGroupEmissionTransportMedium(TransportMediumEntity groupEmissionTransportMedium);

    /**
	 * Getter for the Group of this {@link GroupEmission}
	 * @return {@link Group} Group of this {@link GroupEmission}
	 */
    public GroupEntity getGroup();

    /**
   	 * Setter for the Group of this {@link GroupEmission}
   	 * @param group - {@link GroupEntity} New Group of this {@link GroupEmission}
   	 */
    public void setGroup(GroupEntity group);

    /**
	 * Getter for the CO2 Emission of this {@link GroupEmission}
	 * @return {@code double} CO2 Emission of this {@link GroupEmission}
	 */
    public double getGroupEmission();

    /**
   	 * Setter for the CO2 Emission of this {@link GroupEmission}
   	 * @param groupEmission - {@code double} New CO2 Emission of this {@link GroupEmission}
   	 */
    public void setGroupEmission(double groupEmission);

    /**
	 * Getter for the Score of this {@link GroupEmission}
	 * @return {@code double} Score of this {@link GroupEmission}
	 */
    public double getGroupEmissionScore();

    /**
   	 * Setter for the Score of this {@link GroupEmission}
   	 * @param groupEmissionScore - {@code double} New score of this {@link GroupEmission}
   	 */
    public void setGroupEmissionScore(double groupEmissionScore);

    /**
	 * Getter for the creation date of this {@link GroupEmission}
	 * @return {@link Date} creation date of this {@link GroupEmission}
	 */
    public Date getGroupEmissionCreateDate();

    /**
   	 * Setter for the Creation Date of this {@link GroupEmission}
   	 * @param groupEmissionCreateDate - {@link Date} New Creation Date of this {@link GroupEmission}
   	 */
    public void setGroupEmissionCreateDate(Date groupEmissionCreateDate);

}
