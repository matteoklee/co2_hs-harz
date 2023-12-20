package de.kleemann.co2_hsharz.core.transport.fuel;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is the Implementation of a {@link TransportMediumFuelInterface}
 *
 * @author Fabian Siemens
 * @version 1.0
 * @since 06.12.2023
 */
public class TransportMediumFuelImpl implements TransportMediumFuelInterface {

	/**
	 * {@link TransportMediumFuel} used to construct this {@link TransportMediumFuelImpl}
	 */
	@Getter
	private TransportMediumFuel transportMediumFuel;
	
	/**
	 * Display Name of this {@link TransportMediumFuelImpl}. <br>
	 * Initialized with the first entry of {@link TransportMediumFuelImpl#getTransportMediumFuelAliases()}
	 */
	@Setter
	private String transportMediumFuelDisplayName;
	
	/**
	 * Constructs a new {@link TransportMediumFuelImpl} using a {@link TransportMediumFuel}
	 * @param transportMediumFuel - {@link TransportMediumFuel}
	 */
	public TransportMediumFuelImpl(TransportMediumFuel transportMediumFuel) {
		this.transportMediumFuel = transportMediumFuel;
		
		//initialize the displayname with first entry of aliases, if array has entries
		if(this.getTransportMediumFuelAliases().length > 0)
			this.transportMediumFuelDisplayName = getTransportMediumFuelAliases()[0];
		//else set it to TransportMediumFuel#toString() to avoid null
		else
			this.transportMediumFuelDisplayName = this.transportMediumFuel.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTransportMediumFuelId() {
		return this.transportMediumFuel.ordinal();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTransportMediumFuelName() {
		return this.transportMediumFuel.name();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getTransportMediumFuelAliases() {
		return this.transportMediumFuel.getTransportMediumFuelStrings();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTransportMediumFuelDisplayName() {
		return this.transportMediumFuelDisplayName;
	}
}
