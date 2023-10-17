package de.kleemann.co2_hsharz.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class "UserRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);

}
