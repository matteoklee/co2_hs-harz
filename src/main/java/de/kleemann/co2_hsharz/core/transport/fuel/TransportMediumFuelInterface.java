package de.kleemann.co2_hsharz.core.transport.fuel;

import de.kleemann.co2_hsharz.core.transport.TransportMedium;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;

/**
 * This Interface represents a {@link TransportMediumFuel} on the core layer <br>
 * A {@link TransportMediumFuel} is the fuel used by a {@link TransportMedium} and can be any of the enum constants of {@link TransportMediumFuel} <br>
 * It has an Id, Name, Aliases (Name variants) and defines a DisplayName
 *
 * @author Fabian Siemens
 * @version 1.0
 * @since 30.11.2023
 */
public interface TransportMediumFuelInterface {

	/**
	 * Getter for the Id (Ordinal) of the {@link TransportMediumFuel}
	 * @return {@code int} Id / Ordinal
	 */
	public int getTransportMediumFuelId();

	/**
	 * Getter for the Name of this {@link TransportMediumFuel}
	 * @return {@link String} Name
	 */
	public String getTransportMediumFuelName();

	/**
	 * Getter for the Array of Aliases of this {@link TransportMediumFuel}
	 * @return {@link String}[] Aliases
	 */
	public String[] getTransportMediumFuelAliases();

	/**
	 * Getter for the DisplayName of this {@link TransportMediumFuel}
	 * @return {@link String} DisplayName
	 */
	public String getTransportMediumFuelDisplayName();

}
