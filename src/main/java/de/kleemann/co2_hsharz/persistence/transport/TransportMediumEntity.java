package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This Entity represents a {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Entity
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class TransportMediumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transportMediumId;
    @NonNull
    private String transportMediumFileName;
    @NonNull
    private TransportMediumName transportMediumName;
    private TransportMediumSize transportMediumSize;
    private TransportMediumFuel transportMediumFuel;
    private double transportMediumConsumption;
    private double transportMediumVersion;

    public TransportMediumEntity(long transportMediumId) {
        setTransportMediumId(transportMediumId);
    }

    /**
     * Compares this Object to another object
     * <hr>
     * {@inheritDoc}
     */
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

    /**
     * Returns a JSON-String of this Entity
     * <hr>
     * {@inheritDoc}
     */
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
