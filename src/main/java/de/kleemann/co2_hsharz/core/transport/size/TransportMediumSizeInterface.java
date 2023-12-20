package de.kleemann.co2_hsharz.core.transport.size;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

public interface TransportMediumSizeInterface {

	/**
	 * Getter for the Id (Ordinal) of the {@link TransportMediumSize}
	 * @return {@code int} Id / Ordinal
	 */
	public int getTransportMediumSizeId();

	/**
	 * Getter for the Name of this {@link TransportMediumSize}
	 * @return {@link String} Name
	 */
	public String getTransportMediumSizeName();

	/**
	 * Getter for the Array of Aliases of this {@link TransportMediumSize}
	 * @return {@link String}[] Aliases
	 */
	public String[] getTransportMediumSizeAliases();

	/**
	 * Getter for the DisplayName of this {@link TransportMediumSize}
	 * @return {@link String} DisplayName
	 */
	public String getTransportMediumSizeDisplayName();

}
