package de.kleemann.co2_hsharz.api.auth;

import de.kleemann.co2_hsharz.core.auth.User;
import de.kleemann.co2_hsharz.core.auth.UserService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import lombok.NonNull;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This API Controller offers the Authorization Endpoints {@code /users} and {@code /register}
 * <br>
 * <li> GET /users returns a list of all users
 * <li> POST /register is used to create a new user and returns the created user
 * 
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@RestController()
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    /**
     * Constructor with auto-injection of a {@link UserService}-Bean
     * @param userService - {@link UserService}-Bean
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Returns a list of all registered users <br>
     * Requires the requesting party to have the ADMIN Role
     * @return {@link ResponseEntity} containing a {@link List} of {@link UserDTO}s
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList()));
    }

    /**
     * Registeres a new user
     * @param userDTO - Non Null {@link UserDTO} containing user credentials
     * @return {@link ResponseEntity} containing the created {@link UserDTO}
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@NonNull @RequestBody UserDTO userDTO) {

        if(userService.isUserExisting(userDTO.getUserName())) {
            throw new CustomIllegalArgumentException("userName already exists");
        }
        
        User user = convertToUser(userDTO);
        final User persistedUser = userService.persistUser(user);
        return ResponseEntity.ok(convertToUserDTO(persistedUser));
    }

    /**
     * Converts a {@link User} to a {@link UserDTO}
     * @param user - Non Null {@link User}-Object to convert
     * @return {@link UserDTO}-Object converted from {@code user}
     */
    private UserDTO convertToUserDTO(@NonNull User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPassword(user.getUserPassword());
        userDTO.setUserRole(user.getUserRole());
        return userDTO;
    }

    /**
     * Converts a {@link UserDTO} to a {@link User}
     * @param userDTO - Non Null {@link UserDTO}-Object to convert
     * @return {@link User}-Object converted from {@code userDTO}
     */
    private User convertToUser(@NonNull UserDTO userDTO) {
        User user = userService.createUser();
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserRole(userDTO.getUserRole());
        return user;
    }

}
