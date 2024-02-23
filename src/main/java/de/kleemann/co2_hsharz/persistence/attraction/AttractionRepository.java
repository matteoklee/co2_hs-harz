package de.kleemann.co2_hsharz.persistence.attraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Interface is a {@link JpaRepository} for the {@link AttractionEntity}
 * 
 * This class is deprecated and pending removal
 */
@Deprecated
@Repository
public interface AttractionRepository extends JpaRepository<AttractionEntity, Long> {

}
