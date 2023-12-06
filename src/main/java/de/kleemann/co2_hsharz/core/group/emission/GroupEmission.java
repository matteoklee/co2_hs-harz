package de.kleemann.co2_hsharz.core.group.emission;

import de.kleemann.co2_hsharz.persistence.group.GroupEntity;

import java.util.Date;

/**
 * Class "GroupEmission" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public interface GroupEmission {

    public long getGroupEmissionId();

    public void setGroupEmissionId(long groupEmissionId);

    public String getGroupEmissionStartLocation();

    public void setGroupEmissionStartLocation(String groupEmissionStartLocation);

    public String getGroupEmissionEndLocation();

    public void setGroupEmissionEndLocation(String groupEmissionEndLocation);

    public boolean isGroupEmissionCustomTransportMedium();

    public void setGroupEmissionCustomTransportMedium(boolean groupEmissionCustomTransportMedium);
    public long getGroupEmissionTransportMediumId();

    public void setGroupEmissionTransportMediumId(long groupEmissionTransportMediumId);
    public GroupEntity getGroup();

    public void setGroup(GroupEntity group);

    public double getGroupEmission();

    public void setGroupEmission(double groupEmission);

    public double getGroupEmissionScore();

    public void setGroupEmissionScore(double groupEmissionScore);

    public Date getGroupEmissionCreateDate();

    public void setGroupEmissionCreateDate(Date groupEmissionCreateDate);

}
