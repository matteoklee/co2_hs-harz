package de.kleemann.co2_hsharz.core.transport.fuel;

public interface TransportMediumFuelInterface {

	public int getTransportMediumFuelId();

	public String getTransportMediumFuelName();

	public String[] getTransportMediumFuelAliases();

	public String getTransportMediumFuelDisplayName(String languageCode);

}
