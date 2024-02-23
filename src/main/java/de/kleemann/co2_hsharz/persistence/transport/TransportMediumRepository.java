package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * This Interface is a {@link JpaRepository} for the {@link TransportMediumEntity} <br>
 * It defines Query Methods to find {@link TransportMediumEntity}s by several different attributes, 
 * as well as a method that checks the existence of a {@link TransportMediumEntity} by its file name
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Component
@Repository
public interface TransportMediumRepository extends JpaRepository<TransportMediumEntity, Long> {

    TransportMediumEntity getFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuelOrderByTransportMediumVersionDesc(TransportMediumName transportMediumName, TransportMediumSize transportMediumSize, TransportMediumFuel transportMediumFuel);

    TransportMediumEntity getFirstByTransportMediumNameOrderByTransportMediumVersionDesc(TransportMediumName transportMediumName);
    TransportMediumEntity getFirstByTransportMediumNameAndTransportMediumFuelOrderByTransportMediumVersionDesc(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel);

    boolean existsByTransportMediumFileName(String transportMediumFileName);

    TransportMediumEntity getFirstByTransportMediumFileNameOrderByTransportMediumVersionDesc(String transportMediumFileName);

}
