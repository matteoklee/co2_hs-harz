package de.kleemann.co2_hsharz.api.auth;

/**
 * This Class is a Data Transfer Object for the {@link UserController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */

public class UserDTO {

    private long userId;
    private String userName;
    private String userPassword;
    private String userRole;

    /**
     * Default Constructor. Use Setters to insert values
     */
    public UserDTO() {

    }

    /**
     * Getter for {@code UserDTO#userId}
     * @return {@code long} ID of this User
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Setter for {@code UserDTO#userId}
     * @param userId - New ID of this User
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Getter for {@code UserDTO#userName}
     * @return {@link String} Name of this User
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for {@code UserDTO#userName}
     * @param userName - New Name of this User
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for {@code UserDTO#userPassword}
     * @return {@link String} Password of this User
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Setter for {@code UserDTO#userPassword}
     * @param userPassword - New Password of this User
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Getter for {@code UserDTO#userRole}
     * @return {@link String} Role of this User
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Setter for {@code UserDTO#userRole}
     * @param userRole - New Role of this User
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
