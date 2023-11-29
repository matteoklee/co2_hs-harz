package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumSize;

public interface TransportMedium {

    public long getTransportMediumId();

    public void setTransportMediumId(long transportMediumId);

    public String getTransportMediumFileName();

    public void setTransportMediumFileName(String transportMediumFileName);

    public TransportMediumName getTransportMediumName();

    public void setTransportMediumName(TransportMediumName transportMediumName);

    public TransportMediumSize getTransportMediumSize();

    public void setTransportMediumSize(TransportMediumSize transportMediumSize);

    public TransportMediumFuel getTransportMediumFuel();

    public void setTransportMediumFuel(TransportMediumFuel transportMediumFuel);

    public double getTransportMediumConsumption();

    public void setTransportMediumConsumption(double transportMediumConsumption);

}
