package de.kleemann.co2_hsharz.core.auth;

import de.kleemann.co2_hsharz.persistence.auth.UserEntity;
import lombok.NonNull;

/**
 * User are saved as Entities in our Database. 
 * This class reflects a UserEntity in the core layer of the project
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */

public class User {

	/**
	 * {@link UserEntity} upon which this {@link User} was constructed
	 */
    private final UserEntity userEntity;

    /**
     * Constructs a new {@link User} from an {@link UserEntity}
     * @param userEntity - {@link UserEntity}
     * @throws IllegalArgumentException if {@code userEntity} is null
     */
    public User(@NonNull final UserEntity userEntity) {
        if(userEntity == null) {
            throw new IllegalArgumentException("userEntity may not be null.");
        }
        this.userEntity = userEntity;
    }

    /**
     * Getter for {@code User#userEntity}
     * @return {@link UserEntity} used to construct this User
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * Getter for the ID of this User
     * @return {@code long} Id of this User
     */
    public long getUserId() {
        return this.userEntity.getUserId();
    }

    /**
     * Setter for the ID of this User
     * @param userId - {@code long} New ID of this User
     */
    public void setUserId(long userId) {
        this.userEntity.setUserId(userId);
    }

    /**
     * Getter for the Name of this User
     * @return {@link String} Name of this User
     */
    public String getUserName() {
        return this.userEntity.getUserName();
    }

    /**
     * Setter for the Name of this User
     * @param userName - {@link String} New Name of this User
     */
    public void setUserName(String userName) {
        this.userEntity.setUserName(userName);
    }

    /**
     * Getter for the Password of this User
     * @return {@link String} Password of this User
     */
    public String getUserPassword() {
        return this.userEntity.getUserPassword();
    }

    /**
     * Setter for the Password of this User
     * @param userPassword - {@link String} New Password of this User
     */
    public void setUserPassword(String userPassword) {
        this.userEntity.setUserPassword(userPassword);
    }

    /**
     * Getter for the Role of this User
     * @return {@link String} Role of this User
     */
    public String getUserRole() {
        return this.userEntity.getUserRole();
    }

    /**
     * Setter for the Role of this User
     * @param userRole - {@link String} New Role of this User
     */
    public void setUserRole(String userRole) {
        this.userEntity.setUserRole(userRole);
    }

}
