package de.kleemann.co2_hsharz.persistence.transport;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;

/**
 * Class "TransportMediumRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Component
@Repository
public interface TransportMediumRepository extends JpaRepository<TransportMediumEntity, Long> {

    TransportMediumEntity findFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel(TransportMediumName transportMediumName, TransportMediumSize transportMediumSize, TransportMediumFuel transportMediumFuel);

    TransportMediumEntity findFirstByTransportMediumName(TransportMediumName transportMediumName);
    TransportMediumEntity findFirstByTransportMediumNameAndTransportMediumFuel(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel);

    boolean existsByTransportMediumFileName(String transportMediumFileName);

}
