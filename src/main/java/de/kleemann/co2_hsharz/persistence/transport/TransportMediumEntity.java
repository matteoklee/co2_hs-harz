package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.core.transport.TransportMediumType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Class "TransportMediumEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Entity
public class TransportMediumEntity {

    @Id
    @GeneratedValue
    private long transportId;
    private String transportName;
    private TransportMediumType transportMediumType;
    private double consumption;

    public TransportMediumEntity() {

    }

    public TransportMediumEntity(long transportId) {
        setTransportId(transportId);
    }

    public long getTransportId() {
        return transportId;
    }

    public void setTransportId(long transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public TransportMediumType getTransportMediumType() {
        return transportMediumType;
    }

    public void setTransportMediumType(TransportMediumType transportMediumType) {
        this.transportMediumType = transportMediumType;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
