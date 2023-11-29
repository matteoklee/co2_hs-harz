package de.kleemann.co2_hsharz.persistence.transport;

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
		transportMedium.setTransportMediumName("testMedium");
		transportMedium.setTransportMediumSize(TransportMediumSize.MEDIUM);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityB() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.LPG);
		transportMedium.setTransportMediumConsumption(11);
		transportMedium.setTransportMediumId(100);
		transportMedium.setTransportMediumName("testLarge");
		transportMedium.setTransportMediumSize(TransportMediumSize.LARGE);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityC() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.PETROL);
		transportMedium.setTransportMediumConsumption(5);
		transportMedium.setTransportMediumId(101);
		transportMedium.setTransportMediumName("testSmall");
		transportMedium.setTransportMediumSize(TransportMediumSize.SMALL);
		return transportMedium;
	}
	
	public static TransportMediumEntity createTransportMediumEntityD() {
		TransportMediumEntity transportMedium = new TransportMediumEntity();
		transportMedium.setTransportMediumFuel(TransportMediumFuel.CNG);
		transportMedium.setTransportMediumConsumption(20);
		transportMedium.setTransportMediumId(102);
		transportMedium.setTransportMediumName("testCNG");
		transportMedium.setTransportMediumSize(TransportMediumSize.LARGE);
		return transportMedium;
	}
}
