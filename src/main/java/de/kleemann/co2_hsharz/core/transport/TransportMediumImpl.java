package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import lombok.NonNull;

/**
 * This class is the Implementation of a {@link TransportMedium}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public class TransportMediumImpl implements TransportMedium {

	/**
	 * {@link TransportMediumEntity}, from which this {@link TransportMediumImpl} was constructed
	 */
    private final TransportMediumEntity transportMediumEntity;

    /**
     * Constructs a new {@link TransportMediumImpl} from a {@link TransportMediumEntity}
     * @param transportMediumEntity - {@link TransportMediumEntity}
     * @throws CustomIllegalArgumentException if transportMediumEntity is null
     */
    public TransportMediumImpl(@NonNull TransportMediumEntity transportMediumEntity) {
        if(transportMediumEntity == null) {
            throw new CustomIllegalArgumentException("transportMediumEntity must not be null.");
        }
        this.transportMediumEntity = transportMediumEntity;
    }


    /** {@inheritDoc} */
    @Override
    public long getTransportMediumId() {
        return this.transportMediumEntity.getTransportMediumId();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumId(long transportMediumId) {
        this.transportMediumEntity.setTransportMediumId(transportMediumId);
    }

    /** {@inheritDoc} */
    @Override
    public String getTransportMediumFileName() {
        return this.transportMediumEntity.getTransportMediumFileName();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumFileName(String transportMediumFileName) {
        this.transportMediumEntity.setTransportMediumFileName(transportMediumFileName);
    }

    /** {@inheritDoc} */
    @Override
    public TransportMediumName getTransportMediumName() {
        return this.transportMediumEntity.getTransportMediumName();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumName(TransportMediumName transportMediumName) {
        this.transportMediumEntity.setTransportMediumName(transportMediumName);
    }

    /** {@inheritDoc} */
    @Override
    public TransportMediumSize getTransportMediumSize() {
        return this.transportMediumEntity.getTransportMediumSize();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumSize(TransportMediumSize transportMediumSize) {
        this.transportMediumEntity.setTransportMediumSize(transportMediumSize);
    }

    /** {@inheritDoc} */
    @Override
    public TransportMediumFuel getTransportMediumFuel() {
        return this.transportMediumEntity.getTransportMediumFuel();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumFuel(TransportMediumFuel transportMediumFuel) {
        this.transportMediumEntity.setTransportMediumFuel(transportMediumFuel);
    }

    /** {@inheritDoc} */
    @Override
    public double getTransportMediumConsumption() {
        return this.transportMediumEntity.getTransportMediumConsumption();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumConsumption(double transportMediumConsumption) {
        this.transportMediumEntity.setTransportMediumConsumption(transportMediumConsumption);
    }

    /** {@inheritDoc} */
    @Override
    public double getTransportMediumVersion() {
        return this.transportMediumEntity.getTransportMediumVersion();
    }

    /** {@inheritDoc} */
    @Override
    public void setTransportMediumVersion(double transportMediumVersion) {
        this.transportMediumEntity.setTransportMediumVersion(transportMediumVersion);
    }

    /**
     * Returns the {@link TransportMediumEntity} this {@link TransportMediumImpl} was constructed from
     * @return {@link TransportMediumEntity} used to construct this
     */
    public TransportMediumEntity getTransportMediumEntity() {
        return transportMediumEntity;
    }
}
