package de.kleemann.co2_hsharz.api.emission;

import java.util.LinkedList;
import java.util.List;

import de.kleemann.co2_hsharz.api.emission.dto.TransportMediumDTO;

public class EmissionsCalculationTestsUtil {
	/**
	 * Should not be instantiated
	 * @throws UnsupportedOperationException
	 */
	private EmissionsCalculationTestsUtil(){
		throw new UnsupportedOperationException("Utility classes shouldn't be constructed");
	}
	
	public static Object[][] transportMediumNameInputs = {	{null, false}, {"", false}, {"1234", false}, {"bullshit", false}, 
															{"pkw", true}, {"car", true}, {"PKW", true}, {"Default", false}, 
															{"Buslinie", true}, {"bUsrEise", true}, {"zug", true}, {"Train", true}, 
															{"Fahrrad", true}, {"BIKE", true}, {"Fuß", true}, {"foot", true}};
	public static Object[][] transportMediumSizeInputs = {	{null, false}, {"", false}, {"1234", false}, {"bullshit", false},
															{"klein", true}, {"SMALL", true}, {"mittel", true}, {"Medium", true}, 
															{"gross", true}, {"large", true}, {"1", true}};
	public static Object[][] transportMediumFuelInputs = {	{null, false}, {"", false}, {"1234", false}, {"bullshit", false}, 
															{"otto", true}, {"petrol", true}, {"benzin", true}, {"Default", false}, 
															{"diesel", true}, {"PHEV_Diesel", true}, {"phev_otto", true}, {"elektro", false},
															{"electric", true}, {"erdgas", true}, {"cng", true}, {"autogas", false}, {"lpg", false}};
	
	public static class TransportMediumDTOValidation extends TransportMediumDTO {

		private boolean valid;
		
		public TransportMediumDTOValidation(String transportMediumName, String transportMediumSize,
				String transportMediumFuel, boolean valid) {
			super(transportMediumName, transportMediumSize, transportMediumFuel);
			this.valid = valid;
		}
		
		public boolean isValid() {
			return valid;
		}
		
		@Override
		public String toString() {
			return "name=" + getTransportMediumName() + ", size=" + getTransportMediumSize() + ", fuel=" + getTransportMediumFuel() + ", valid=" + isValid();
		}
	}
	
	public static List<TransportMediumDTOValidation> createListOfTransportMediumDTOValidations() {
		List<TransportMediumDTOValidation> dtos = new LinkedList<TransportMediumDTOValidation>();
		
		for(Object[] names : transportMediumNameInputs) {
			boolean nameValid = (boolean)names[1];
			String name = (String)names[0];
			
			for(Object[] sizes : transportMediumSizeInputs) {
				boolean sizeValid = (boolean)sizes[1];
				String size = (String)sizes[0];
				
				for(Object[] fuels : transportMediumFuelInputs) {
					boolean fuelValid = (boolean)fuels[1];
					String fuel = (String)fuels[0];
					
					dtos.add(new TransportMediumDTOValidation(name, size, fuel, fuelValid && nameValid && sizeValid));
				}
			}
		}
		
		return dtos;
	}
}
