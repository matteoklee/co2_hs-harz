package de.kleemann.co2_hsharz.persistence.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class "TransportMediumRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Repository
public interface TransportMediumRepository extends JpaRepository<TransportMediumEntity, Long> {

    TransportMediumEntity findFirstByTransportMediumNameAndTransportMediumSizeAndTransportMediumFuel(TransportMediumName transportMediumName, TransportMediumSize transportMediumSize, TransportMediumFuel transportMediumFuel);

    TransportMediumEntity findFirstByTransportMediumName(TransportMediumName transportMediumName);
    TransportMediumEntity findFirstByTransportMediumNameAndTransportMediumFuel(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel);

    boolean existsByTransportMediumFileName(String transportMediumFileName);

}
