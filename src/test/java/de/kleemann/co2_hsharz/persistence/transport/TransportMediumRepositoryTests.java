package de.kleemann.co2_hsharz.persistence.transport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * This class contains tests for {@link TransportMediumRepository}
 * <li> Test of Built-In CRUD 
 * <li> Test of Custom {@link TransportMediumRepository} Functions
 * 
 * TODO Edgecases
 * 
 * @author Fabian Siemens
 * @version 1.0
 * @since 29.11.2023
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransportMediumRepositoryTests {
	
	private final TransportMediumRepository repos;
	
	@Autowired
	public TransportMediumRepositoryTests(final TransportMediumRepository repos) {
		this.repos = repos;
	}
	
	/**
	 * Tests saving and retrieving a {@link TransportMediumEntity} in/from Database
	 */
	public void testSaveAndFindTransportMediumById() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityA();
		repos.save(medium);
		Optional<TransportMediumEntity> result = repos.findById(medium.getTransportMediumId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(medium);
	}
	
	/**
	 * Tests saving and updating a {@link TransportMediumEntity} in Database
	 */
	@Test
	public void testUpdateTransportMedium() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityB();
		medium = repos.save(medium);
		medium.setTransportMediumFuel(TransportMediumFuel.PETROL);
		repos.save(medium);
		Optional<TransportMediumEntity> result = repos.findById(medium.getTransportMediumId());
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(medium);
	}
	
	/**
	 * Tests saving and deleting a {@link TransportMediumEntity} in Database
	 */
	@Test
	public void testCreateAndDeleteTransportMedium() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityC();
		
		medium = repos.save(medium);
		assertThat(repos.existsById(medium.getTransportMediumId())).isTrue();
		
		repos.delete(medium);
		assertThat(repos.existsById(medium.getTransportMediumId())).isFalse();
	}
	
	/**
	 * Tests retrieving the first {@link TransportMediumEntity} in Database by Name, Size and Fuel
	 */
	@Test
	public void testFindFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityC();
		medium = repos.save(medium);
		TransportMediumEntity result = repos.findFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel(
				medium.getTransportMediumName(), medium.getTransportMediumSize(), medium.getTransportMediumFuel());
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(medium);
	}
	
	/**
	 * Tests retrieving the first {@link TransportMediumEntity} in Database by Name
	 */
	@Test
	public void testFindFirstByTransportMediumNameLike() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityD();
		medium = repos.save(medium);
		TransportMediumEntity result = repos.findFirstByTransportMediumNameLike(medium.getTransportMediumName());
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(medium);
	}
	
	/**
	 * Tests existence of {@link TransportMediumEntity} by filename
	 */
	@Test
	public void testExistsByTransportMediumFileName() {
		TransportMediumEntity medium = TransportMediumTestUtil.createTransportMediumEntityA();
		medium = repos.save(medium);
		assertThat(repos.existsByTransportMediumFileName("thisIsNotAFileName")).isFalse();					//Assert not existing
		assertThat(repos.existsByTransportMediumFileName(medium.getTransportMediumFileName())).isTrue();	//Assert existing
	}
}
