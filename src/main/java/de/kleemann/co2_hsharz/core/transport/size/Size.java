package de.kleemann.co2_hsharz.core.transport.size;

public interface Size {
	public int getId();
	public String getName();
	public String[] getAliases();
	public String getDisplayName(String languageCode);
}
