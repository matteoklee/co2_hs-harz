package de.kleemann.co2_hsharz.core.transport.size;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import lombok.Setter;

/**
 * This class is the Implementation of a {@link TransportMediumSizeInterface}
 *
 * @author Fabian Siemens
 * @version 1.0
 * @since 06.12.2023
 */
public class TransportMediumSizeImpl implements TransportMediumSizeInterface {

	/**
	 * {@link TransportMediumSize} used to construct this {@link TransportMediumSizeImpl}
	 */
	private TransportMediumSize transportMediumSize;
	@Setter
	private String transportMediumSizeDisplayName;
	
	public TransportMediumSizeImpl(TransportMediumSize transportMediumSize) {
		this.transportMediumSize = transportMediumSize;
		this.transportMediumSizeDisplayName = getTransportMediumSizeAliases()[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTransportMediumSizeId() {
		return this.transportMediumSize.ordinal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTransportMediumSizeName() {
		return this.transportMediumSize.name();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTransportMediumSizeAliases() {
		return this.transportMediumSize.getTransportMediumSizeString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTransportMediumSizeDisplayName() {
		return this.transportMediumSizeDisplayName;
	}
}
