package de.kleemann.co2_hsharz.persistence.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class "UserEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Data
@Entity
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private String userPassword;
    private UserRole userRole;

    public UserEntity(long userId) {
        setUserId(userId);
    }
}
