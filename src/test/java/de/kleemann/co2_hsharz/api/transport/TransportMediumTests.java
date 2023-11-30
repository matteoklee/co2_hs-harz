package de.kleemann.co2_hsharz.api.transport;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.kleemann.co2_hsharz.api.emission.EmissionsCalculationController;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumTestUtil;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This class contains tests for {@link EmissionsCalculationController}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 29.11.2023
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransportMediumTests {
	
	@Test
	public void testFindTransportMediumNameFromName() {
		for(Object[] testCase : TransportMediumTestUtil.transportMediumNameInputs) {
			try{		
				TransportMediumName result = TransportMediumName.fromName((String)testCase[0]);
				if(result == null)
					throw new NullPointerException("Result is null");
				if(!(boolean)testCase[1])	//If not valid -> fail
					fail("Name should not be valid, but is: " + (String)testCase[0]);
			}
			catch(Exception e) {
				if((boolean)testCase[1]) {	//If valid -> fail
					e.printStackTrace();
					fail("Name should be valid, but isn't: " + (String)testCase[0]);
				}
			}
		}
	}
	
	@Test
	public void testFindTransportMediumSizeFromName() {
		for(Object[] testCase : TransportMediumTestUtil.transportMediumSizeInputs) {
			try{		
				TransportMediumSize result = TransportMediumSize.fromName((String)testCase[0]);
				
				if(result == null)
					throw new NullPointerException("Result is null");
				
				if(!(boolean)testCase[1])	//If not valid -> fail
					fail("Size should not be valid, but is: " + (String)testCase[0]);
			}
			catch(Exception e) {
				if((boolean)testCase[1]) {	//If valid -> fail
					e.printStackTrace();
					fail("Size should be valid, but isn't: " + (String)testCase[0]);
				}
			}
		}
	}
	
	@Test
	public void testFindTransportMediumFuelFromName() {
		for(Object[] testCase : TransportMediumTestUtil.transportMediumFuelInputs) {
			try{		
				TransportMediumFuel result = TransportMediumFuel.fromName((String)testCase[0]);
				
				if(result == null)
					throw new NullPointerException("Result is null");
				
				if(!(boolean)testCase[1])	//If not valid -> fail
					fail("Fuel should not be valid, but is: " + (String)testCase[0]);
			}
			catch(Exception e) {
				if((boolean)testCase[1]) {	//If valid -> fail
					e.printStackTrace();
					fail("Fuel should be valid, but isn't: " + (String)testCase[0]);
				}
			}
		}
	}
}
