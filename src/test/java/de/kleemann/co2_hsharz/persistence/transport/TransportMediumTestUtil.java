package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.persistence.transport.fuel.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.size.TransportMediumSize;

public class TransportMediumTestUtil {
	
	/**
	 * Should not be instantiated
	 * @throws UnsupportedOperationException
	 */
	private TransportMediumTestUtil(){
		throw new UnsupportedOperationException("Utility classes shouldn't be constructed");
	}
	
	public static TransportMediumEntity createTransportMediumEntityA() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.DIESEL);
		transportMedium.setTransportMediumConsumption(7.9);
		transportMedium.setTransportMediumId(99);
		transportMedium.setTransportMediumFileName("myFileName");
		transportMedium.setTransportMediumName(TransportMediumName.TRAIN);
		transportMedium.setTransportMediumSize(TransportMediumSize.MEDIUM);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityB() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.LPG);
		transportMedium.setTransportMediumConsumption(11);
		transportMedium.setTransportMediumId(100);
		transportMedium.setTransportMediumName(TransportMediumName.CAR);
		transportMedium.setTransportMediumSize(TransportMediumSize.LARGE);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityC() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.PETROL);
		transportMedium.setTransportMediumConsumption(5);
		transportMedium.setTransportMediumId(101);
		transportMedium.setTransportMediumName(TransportMediumName.BUS_TOUR);
		transportMedium.setTransportMediumSize(TransportMediumSize.SMALL);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityD() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.CNG);
		transportMedium.setTransportMediumConsumption(20);
		transportMedium.setTransportMediumId(102);
		transportMedium.setTransportMediumName(TransportMediumName.BUS_PUBLIC);
		transportMedium.setTransportMediumSize(TransportMediumSize.LARGE);
		return transportMedium;
	}
}
