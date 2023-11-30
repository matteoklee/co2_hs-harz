package de.kleemann.co2_hsharz.api.transport;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This class contains tests for {@link TransportMediumController}
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 29.11.2023
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransportControllerTests {
	
	private final TransportMediumController controller;
	
	@Autowired
	public TransportControllerTests(final TransportMediumController controller) {
		this.controller = controller;
	}
	
	public void testFindAllTransportMediums() {
		
	}
}
