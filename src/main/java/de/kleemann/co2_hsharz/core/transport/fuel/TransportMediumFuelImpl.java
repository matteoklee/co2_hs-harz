package de.kleemann.co2_hsharz.core.transport.fuel;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import lombok.Setter;

public class TransportMediumFuelImpl implements TransportMediumFuelInterface {

	private TransportMediumFuel transportMediumFuel;
	@Setter
	private String transportMediumFuelDisplayName;
	
	public TransportMediumFuelImpl(TransportMediumFuel transportMediumFuel) {
		this.transportMediumFuel = transportMediumFuel;
		this.transportMediumFuelDisplayName = getTransportMediumFuelAliases()[0];
	}


	@Override
	public int getTransportMediumFuelId() {
		return this.transportMediumFuel.ordinal();
	}

	@Override
	public String getTransportMediumFuelName() {
		return this.transportMediumFuel.name();
	}

	@Override
	public String[] getTransportMediumFuelAliases() {
		return this.transportMediumFuel.getTransportMediumFuelStrings();
	}

	@Override
	public String getTransportMediumFuelDisplayName(String languageCode) {
		return this.transportMediumFuelDisplayName;
	}
}
