package de.kleemann.co2_hsharz.api.auth;

import lombok.Data;

/**
 * This Class is a Data Transfer Object for the {@link UserController} API Endpoint
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Deprecated
@Data
public class UserDTO {
    private long userId;
    private String userName;
    private String userPassword;
    private String userRole;
}
