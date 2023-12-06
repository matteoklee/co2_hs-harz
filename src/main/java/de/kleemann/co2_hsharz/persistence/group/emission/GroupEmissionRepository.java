package de.kleemann.co2_hsharz.persistence.group.emission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class "GroupEmissionRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Repository
public interface GroupEmissionRepository extends JpaRepository<GroupEmissionEntity, Long> {



}
