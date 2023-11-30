package de.kleemann.co2_hsharz.core.transport.size;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import lombok.Setter;

public class TransportMediumSizeImpl implements TransportMediumSizeInterface {

	private TransportMediumSize transportMediumSize;
	@Setter
	private String transportMediumSizeDisplayName;
	
	public TransportMediumSizeImpl(TransportMediumSize transportMediumSize) {
		this.transportMediumSize = transportMediumSize;
		this.transportMediumSizeDisplayName = getTransportMediumSizeAliases()[0];
	}

	@Override
	public int getTransportMediumSizeId() {
		return this.transportMediumSize.ordinal();
	}

	@Override
	public String getTransportMediumSizeName() {
		return this.transportMediumSize.name();
	}

	@Override
	public String[] getTransportMediumSizeAliases() {
		return this.transportMediumSize.getTransportMediumSizeString();
	}

	@Override
	public String getTransportMediumSizeDisplayName(String languageCode) {
		return this.transportMediumSizeDisplayName;
	}
}
