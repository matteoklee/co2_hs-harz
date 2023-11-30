package de.kleemann.co2_hsharz.core.fuel;

import de.kleemann.co2_hsharz.persistence.fuel.TransportMediumFuel;
import lombok.Setter;

public class FuelImpl implements Fuel {

	private TransportMediumFuel fuel;
	@Setter
	private String displayName;
	
	public FuelImpl(TransportMediumFuel fuel) {
		this.fuel = fuel;
		this.displayName = getAliases()[0];
	}
	
	@Override
	public int getId() {
		return fuel.ordinal();
	}

	@Override
	public String getName() {
		return fuel.name();
	}

	@Override
	public String[] getAliases() {
		return fuel.getTransportMediumFuelStrings();
	}

	@Override
	public String getDisplayName(String languageCode) {
		return displayName;
	}
}
