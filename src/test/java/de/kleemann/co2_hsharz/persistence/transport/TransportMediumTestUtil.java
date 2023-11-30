package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

public class TransportMediumTestUtil {
	
	public static Object[][] transportMediumNameInputs = {	{null, false}, {"", false}, {"1", true}, {"1234", false}, {"bullshit", false}, 
															{"pkw", true}, {"car", true}, {"PKW", true}, {"Default", true}, 
															{"Buslinie", true}, {"bUsrEise", true}, {"zug", true}, {"Train", true}, 
															{"Fahrrad", true}, {"BIKE", true}, {"Fuß", true}, {"foot", true}};
	public static Object[][] transportMediumSizeInputs = {	{null, false}, {"", false}, {"1", true}, {"1234", false}, {"bullshit", false},
															{"klein", true}, {"SMALL", true}, {"mittel", true}, {"Medium", true}, 
															{"gross", true}, {"large", true}, {"1", true}, {"Default", true}};
	public static Object[][] transportMediumFuelInputs = {	{null, false}, {"", false}, {"1", true}, {"1234", false}, {"bullshit", false}, 
															{"otto", true}, {"petrol", true}, {"benzin", true}, {"Default", true}, 
															{"diesel", true}, {"PHEV_Diesel", true}, {"phev_otto", true}, {"elektro", true},
															{"electric", true}, {"erdgas", true}, {"cng", true}, {"autogas", true}, {"lpg", true}};
	
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
		transportMedium.setTransportMediumFuel(TransportMediumFuel.ELECTRIC);
		transportMedium.setTransportMediumConsumption(20);
		transportMedium.setTransportMediumId(-1);
		transportMedium.setTransportMediumName(TransportMediumName.BUS_PUBLIC);
		transportMedium.setTransportMediumSize(TransportMediumSize.LARGE);
		return transportMedium;
	}
}
