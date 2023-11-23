package de.kleemann.co2_hsharz.core.auth;

import de.kleemann.co2_hsharz.persistence.auth.UserEntity;

/**
 * Class "User" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */

public class User {

    private final UserEntity userEntity;

    public User(final UserEntity userEntity) {
        if(userEntity == null) {
            throw new IllegalArgumentException("userEntity may not be null.");
        }
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public long getUserId() {
        return this.userEntity.getUserId();
    }

    public void setUserId(long userId) {
        this.userEntity.setUserId(userId);
    }

    public String getUserName() {
        return this.userEntity.getUserName();
    }

    public void setUserName(String userName) {
        this.userEntity.setUserName(userName);
    }

    public String getUserPassword() {
        return this.userEntity.getUserPassword();
    }

    public void setUserPassword(String userPassword) {
        this.userEntity.setUserPassword(userPassword);
    }

    public String getUserRole() {
        return this.userEntity.getUserRole();
    }

    public void setUserRole(String userRole) {
        this.userEntity.setUserRole(userRole);
    }

}
