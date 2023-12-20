package de.kleemann.co2_hsharz.core.transport;

import de.kleemann.co2_hsharz.persistence.transport.TransportMediumEntity;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This Interface represents a {@link TransportMediumEntity} on the core layer <br>
 * A TransportMedium is a way to travel. Consisting of a vehicle type, fuel type and vehicle size. <br>
 * They are used to calculate the co2 emission on the basis of our data set. <br>
 * It has an Id, FileName, Name, Fuel and Size and version. A TransportMedium can also have a consumption rate. <br>
 * This is an optional value to improve accuracy in our calculations
 * 
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
public interface TransportMedium {

	/**
	 * Getter for the Id of this {@link TransportMedium}
	 * @return {@code long} Id of this {@link TransportMedium}
	 */
    public long getTransportMediumId();

    /**
	 * Setter for the Id of this {@link TransportMedium}
	 * @param transportMediumId - {@code long} New Id of this {@link TransportMedium}
	 */
    public void setTransportMediumId(long transportMediumId);

    /**
	 * Getter for the FileName of this {@link TransportMedium}
	 * @return {@link String} FileName of this {@link TransportMedium}
	 */
    public String getTransportMediumFileName();

    /**
	 * Setter for the filename of this {@link TransportMedium}
	 * @param transportMediumFileName - {@link String} New filename of this {@link TransportMedium}
	 */
    public void setTransportMediumFileName(String transportMediumFileName);

    /**
	 * Getter for the Name of this {@link TransportMedium}
	 * @return {@link TransportMediumName} Name of this {@link TransportMedium}
	 */
    public TransportMediumName getTransportMediumName();

    /**
	 * Setter for the name of this {@link TransportMedium}
	 * @param transportMediumName - {@link TransportMediumName} New name of this {@link TransportMedium}
	 */
    public void setTransportMediumName(TransportMediumName transportMediumName);

    /**
	 * Getter for the Size of this {@link TransportMedium}
	 * @return {@link TransportMediumSize} Size of this {@link TransportMedium}
	 */
    public TransportMediumSize getTransportMediumSize();

    /**
	 * Setter for the size of this {@link TransportMedium}
	 * @param transportMediumSize - {@link TransportMediumSize} New size of this {@link TransportMedium}
	 */
    public void setTransportMediumSize(TransportMediumSize transportMediumSize);

    /**
	 * Getter for the Fuel of this {@link TransportMedium}
	 * @return {@link TransportMediumFuel} Fuel of this {@link TransportMedium}
	 */
    public TransportMediumFuel getTransportMediumFuel();

    /**
	 * Setter for the fuel of this {@link TransportMedium}
	 * @param transportMediumFuel - {@link TransportMediumFuel} New fuel of this {@link TransportMedium}
	 */
    public void setTransportMediumFuel(TransportMediumFuel transportMediumFuel);

    /**
	 * Getter for the custom fuel consumption of this {@link TransportMedium}
	 * @return {@code double} Consumption of this {@link TransportMedium}
	 */
    public double getTransportMediumConsumption();

    /**
	 * Setter for the custom consumption of this {@link TransportMedium}
	 * @param transportMediumConsumption - {@code double} New custom consumption of this {@link TransportMedium}
	 */
    public void setTransportMediumConsumption(double transportMediumConsumption);

    /**
	 * Getter for the Version of this {@link TransportMedium}
	 * @return {@code double} Version of this {@link TransportMedium}
	 */
    public double getTransportMediumVersion();

    /**
	 * Setter for the version of this {@link TransportMedium}
	 * @param transportMediumVersion - {@code double} New version of this {@link TransportMedium}
	 */
    public void setTransportMediumVersion(double transportMediumVersion);

}
