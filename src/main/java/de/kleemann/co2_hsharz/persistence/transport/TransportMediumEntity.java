package de.kleemann.co2_hsharz.persistence.transport;

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
    private long transportMediumId;
    private String transportMediumFileName;
    private TransportMediumName transportMediumName;
    private TransportMediumSize transportMediumSize;
    private TransportMediumFuel transportMediumFuel;
    private double transportMediumConsumption;

    public TransportMediumEntity() {

    }

    public TransportMediumEntity(long transportMediumId) {
        setTransportMediumId(transportMediumId);
    }

    public long getTransportMediumId() {
        return transportMediumId;
    }

    public void setTransportMediumId(long transportMediumId) {
        this.transportMediumId = transportMediumId;
    }

    public String getTransportMediumFileName() {
        return transportMediumFileName;
    }

    public void setTransportMediumFileName(String transportMediumFileName) {
        this.transportMediumFileName = transportMediumFileName;
    }

    public TransportMediumName getTransportMediumName() {
        return transportMediumName;
    }

    public void setTransportMediumName(TransportMediumName transportMediumName) {
        this.transportMediumName = transportMediumName;
    }

    public TransportMediumSize getTransportMediumSize() {
        return transportMediumSize;
    }

    public void setTransportMediumSize(TransportMediumSize transportMediumSize) {
        this.transportMediumSize = transportMediumSize;
    }

    public TransportMediumFuel getTransportMediumFuel() {
        return transportMediumFuel;
    }

    public void setTransportMediumFuel(TransportMediumFuel transportMediumFuel) {
        this.transportMediumFuel = transportMediumFuel;
    }

    public double getTransportMediumConsumption() {
        return transportMediumConsumption;
    }

    public void setTransportMediumConsumption(double transportMediumConsumption) {
        this.transportMediumConsumption = transportMediumConsumption;
    }

    @Override
    public String toString() {
        return "TransportMediumEntity{" +
                "transportMediumId=" + transportMediumId +
                ", transportMediumFileName='" + transportMediumFileName +
                ", transportMediumName='" + transportMediumName +
                ", transportMediumSize=" + transportMediumSize +
                ", transportMediumFuel=" + transportMediumFuel +
                ", transportMediumConsumption=" + transportMediumConsumption +
                '}';
    }
}
