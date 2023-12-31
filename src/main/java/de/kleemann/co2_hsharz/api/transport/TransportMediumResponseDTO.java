package de.kleemann.co2_hsharz.api.transport;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumSize;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Class "TransportMediumResponseDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 29.11.2023
 */
public class TransportMediumResponseDTO {

    private long transportMediumId;
    private String transportMediumFileName;
    private TransportMediumName transportMediumName;
    private TransportMediumSize transportMediumSize;
    private TransportMediumFuel transportMediumFuel;
    private double transportMediumConsumption;


    public TransportMediumResponseDTO() {

    }

    public TransportMediumResponseDTO(long transportMediumId, String transportMediumFileName, TransportMediumName transportMediumName, TransportMediumSize transportMediumSize, TransportMediumFuel transportMediumFuel, double transportMediumConsumption) {
        this.transportMediumId = transportMediumId;
        this.transportMediumFileName = transportMediumFileName;
        this.transportMediumName = transportMediumName;
        this.transportMediumSize = transportMediumSize;
        this.transportMediumFuel = transportMediumFuel;
        this.transportMediumConsumption = transportMediumConsumption;
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
}
