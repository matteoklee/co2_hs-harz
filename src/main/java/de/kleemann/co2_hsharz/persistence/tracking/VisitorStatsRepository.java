package de.kleemann.co2_hsharz.persistence.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class "VisitorStatsRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Repository
public interface VisitorStatsRepository extends JpaRepository<VisitorStatsEntity, Long> {
}
