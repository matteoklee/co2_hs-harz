package de.kleemann.co2_hsharz.core.transport.size;

import de.kleemann.co2_hsharz.persistence.transport.size.TransportMediumSize;
import lombok.Setter;

public class SizeImpl implements Size {

	private TransportMediumSize size;
	@Setter
	private String displayName;
	
	public SizeImpl(TransportMediumSize size) {
		this.size = size;
		this.displayName = getAliases()[0];
	}
	
	@Override
	public int getId() {
		return size.ordinal();
	}

	@Override
	public String getName() {
		return size.name();
	}

	@Override
	public String[] getAliases() {
		return size.getTransportMediumSizeString();
	}

	@Override
	public String getDisplayName(String languageCode) {
		return displayName;
	}
	
}
