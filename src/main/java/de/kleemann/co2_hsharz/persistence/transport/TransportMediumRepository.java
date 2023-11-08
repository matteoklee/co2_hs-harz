package de.kleemann.co2_hsharz.persistence.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class "TransportMediumRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Repository
public interface TransportMediumRepository extends JpaRepository<TransportMediumEntity, Long> {
}
