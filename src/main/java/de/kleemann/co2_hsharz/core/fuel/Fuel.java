package de.kleemann.co2_hsharz.core.fuel;

public interface Fuel {
	public int getId();
	public String getName();
	public String[] getAliases();
	public String getDisplayName(String languageCode);
}
