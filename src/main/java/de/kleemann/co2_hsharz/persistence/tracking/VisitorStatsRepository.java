package de.kleemann.co2_hsharz.persistence.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Interface is a {@link JpaRepository} for the {@link VisitorStatsEntity}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 31.01.2024
 */
@Repository
public interface VisitorStatsRepository extends JpaRepository<VisitorStatsEntity, Long> {
}
