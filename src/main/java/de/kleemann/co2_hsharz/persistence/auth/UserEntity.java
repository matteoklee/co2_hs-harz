package de.kleemann.co2_hsharz.persistence.auth;

import de.kleemann.co2_hsharz.core.auth.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Class "UserEntity" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;
    private String userName;
    private String userPassword;
    //@Enumerated(EnumType.STRING)
    private String userRole;

    public UserEntity() {

    }

    public UserEntity(long userId) {
        setUserId(userId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
