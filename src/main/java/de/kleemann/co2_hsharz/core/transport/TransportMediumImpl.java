package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * Class "TransportMedium" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class TransportMediumImpl implements TransportMedium {

    private final TransportMediumEntity transportMediumEntity;

    public TransportMediumImpl(TransportMediumEntity transportMediumEntity) {
        if(transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null.");
        }
        this.transportMediumEntity = transportMediumEntity;
    }


    @Override
    public long getTransportMediumId() {
        return this.transportMediumEntity.getTransportMediumId();
    }

    @Override
    public void setTransportMediumId(long transportMediumId) {
        this.transportMediumEntity.setTransportMediumId(transportMediumId);
    }

    @Override
    public String getTransportMediumFileName() {
        return this.transportMediumEntity.getTransportMediumFileName();
    }

    @Override
    public void setTransportMediumFileName(String transportMediumFileName) {
        this.transportMediumEntity.setTransportMediumFileName(transportMediumFileName);
    }

    @Override
    public TransportMediumName getTransportMediumName() {
        return this.transportMediumEntity.getTransportMediumName();
    }

    @Override
    public void setTransportMediumName(TransportMediumName transportMediumName) {
        this.transportMediumEntity.setTransportMediumName(transportMediumName);
    }

    @Override
    public TransportMediumSize getTransportMediumSize() {
        return this.transportMediumEntity.getTransportMediumSize();
    }

    @Override
    public void setTransportMediumSize(TransportMediumSize transportMediumSize) {
        this.transportMediumEntity.setTransportMediumSize(transportMediumSize);
    }

    @Override
    public TransportMediumFuel getTransportMediumFuel() {
        return this.transportMediumEntity.getTransportMediumFuel();
    }

    @Override
    public void setTransportMediumFuel(TransportMediumFuel transportMediumFuel) {
        this.transportMediumEntity.setTransportMediumFuel(transportMediumFuel);
    }

    @Override
    public double getTransportMediumConsumption() {
        return this.transportMediumEntity.getTransportMediumConsumption();
    }

    @Override
    public void setTransportMediumConsumption(double transportMediumConsumption) {
        this.transportMediumEntity.setTransportMediumConsumption(transportMediumConsumption);
    }

    @Override
    public double getTransportMediumVersion() {
        return this.transportMediumEntity.getTransportMediumVersion();
    }

    @Override
    public void setTransportMediumVersion(double transportMediumVersion) {
        this.transportMediumEntity.setTransportMediumVersion(transportMediumVersion);
    }


    public TransportMediumEntity getTransportMediumEntity() {
        return transportMediumEntity;
    }
}
