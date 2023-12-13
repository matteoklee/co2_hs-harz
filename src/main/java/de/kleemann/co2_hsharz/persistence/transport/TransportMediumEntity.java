package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transportMediumId;
    private String transportMediumFileName;
    private TransportMediumName transportMediumName;
    private TransportMediumSize transportMediumSize;
    private TransportMediumFuel transportMediumFuel;
    private double transportMediumConsumption;
    private double transportMediumVersion;

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

    public double getTransportMediumVersion() {
        return transportMediumVersion;
    }

    public void setTransportMediumVersion(double transportMediumVersion) {
        this.transportMediumVersion = transportMediumVersion;
    }

    @Override
	public boolean equals(Object obj) {
    	if(obj == null || !(obj instanceof TransportMediumEntity))
    		return false;
    	
    	TransportMediumEntity entity = (TransportMediumEntity) obj;
    	
		return entity.getTransportMediumId() == this.getTransportMediumId()
				&& entity.getTransportMediumConsumption() == this.getTransportMediumConsumption()
				&& (entity.getTransportMediumFileName() == null ? 
						this.getTransportMediumFileName() == null : 
						entity.getTransportMediumFileName().equals(this.getTransportMediumFileName()))
				&& (entity.getTransportMediumFuel() == null ? 
						this.getTransportMediumFuel() == null :
						entity.getTransportMediumFuel().equals(this.getTransportMediumFuel()))
				&& (entity.getTransportMediumName() == null ? 
						this.getTransportMediumName() == null : 
						entity.getTransportMediumName().equals(this.getTransportMediumName()))
				&& (entity.getTransportMediumSize() == null ? 
						this.getTransportMediumSize() == null : 
						entity.getTransportMediumSize().equals(this.getTransportMediumSize()));
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
                ", transportMediumVersion=" + transportMediumVersion +
                '}';
    }
}
