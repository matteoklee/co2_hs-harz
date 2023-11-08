package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;

/**
 * Class "TransportMedium" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class TransportMedium {

    private final TransportMediumEntity transportMediumEntity;

    public TransportMedium(TransportMediumEntity transportMediumEntity) {
        if(transportMediumEntity == null) {
            throw new IllegalArgumentException("transportMediumEntity must not be null.");
        }
        this.transportMediumEntity = transportMediumEntity;
    }


    public long getTransportId() {
        return transportMediumEntity.getTransportId();
    }

    public void setTransportId(long transportId) {
        this.transportMediumEntity.setTransportId(transportId);
    }

    public String getTransportName() {
        return this.transportMediumEntity.getTransportName();
    }

    public void setTransportName(String transportName) {
        this.transportMediumEntity.setTransportName(transportName);
    }

    public TransportMediumType getTransportMediumType() {
        return this.transportMediumEntity.getTransportMediumType();
    }

    public void setTransportMediumType(TransportMediumType transportMediumType) {
        this.transportMediumEntity.setTransportMediumType(transportMediumType);
    }

    public double getConsumption() {
        return this.transportMediumEntity.getConsumption();
    }

    public void setConsumption(double consumption) {
        this.transportMediumEntity.setConsumption(consumption);
    }

}
