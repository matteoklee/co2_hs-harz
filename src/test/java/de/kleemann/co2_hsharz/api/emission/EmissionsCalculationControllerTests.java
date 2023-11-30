package de.kleemann.co2_hsharz.api.emission;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.kleemann.co2_hsharz.api.emission.EmissionsCalculationTestsUtil.TransportMediumDTOValidation;
import de.kleemann.co2_hsharz.core.transport.TransportMediumService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This class contains tests for {@link EmissionsCalculationController}
 * 
 * TODO Everything
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 29.11.2023
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmissionsCalculationControllerTests {
	
	private final EmissionsCalculationController controller;
	private final TransportMediumService transportMediumService;
	
	@Autowired
	public EmissionsCalculationControllerTests(final EmissionsCalculationController controller, final TransportMediumService transportMediumService) {
		this.controller = controller;
		this.transportMediumService = transportMediumService;
	}
	
	/**
	 * Testet API mit korrekten Inputs
	 */
	@Test
	public void testFindTransportMediumByNameAndSizeAndFuel() {
		List<TransportMediumDTOValidation> validationEntities = EmissionsCalculationTestsUtil.createListOfTransportMediumDTOValidations();
		for(TransportMediumDTOValidation entity : validationEntities) {
			try{		
				transportMediumService.findTransportMediumByNameAndSizeAndFuel(
					TransportMediumName.fromName(entity.getTransportMediumName()), 
					TransportMediumSize.fromName(entity.getTransportMediumSize()), 
					TransportMediumFuel.fromName(entity.getTransportMediumFuel())
				);
				if(!entity.isValid())
					fail("TransportMediumDTO should not be valid, but is: " + entity.toString());
			}
			catch(Exception e) {
				if(entity.isValid()) {
					System.out.println("Failed Test on TransportMediumDTO " + entity.toString() + "; Got Exception: " + e.getLocalizedMessage());
					e.printStackTrace();
					fail("TransportMediumDTO should be valid, but isn't: " + entity.toString());
				}
			}
		}
		
	}
}
