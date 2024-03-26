package de.kleemann.co2_hsharz.core.auth;

import de.kleemann.co2_hsharz.persistence.auth.UserEntity;
import de.kleemann.co2_hsharz.persistence.auth.UserRole;
import lombok.NonNull;

/**
 * Class "User" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */

public class User {

    private final UserEntity userEntity;

    public User(@NonNull final UserEntity userEntity) {
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

    public void setUserName(@NonNull String userName) {
        this.userEntity.setUserName(userName);
    }

    public String getUserPassword() {
        return this.userEntity.getUserPassword();
    }

    public void setUserPassword(@NonNull String userPassword) {
        this.userEntity.setUserPassword(userPassword);
    }

    public UserRole getUserRole() {
        return this.userEntity.getUserRole();
    }

    public void setUserRole(@NonNull UserRole userRole) {
        this.userEntity.setUserRole(userRole);
    }

}
