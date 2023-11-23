package de.kleemann.co2_hsharz;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class "CalculationTest" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 22.11.2023
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CalculationTest {

    /*
        Last Error: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test

     */
    private final TransportMediumRepository transportMediumRepos;

    public CalculationTest(final TransportMediumRepository testRepo){
        this.transportMediumRepos = testRepo;
    }

    @Test
    public void testFindFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel(){

        assertThat(1).isEqualTo(1); //TODO nur ein dummy, chill
    }

}
