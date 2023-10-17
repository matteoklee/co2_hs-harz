package de.kleemann.co2_hsharz.api.auth;

import de.kleemann.co2_hsharz.core.auth.User;
import de.kleemann.co2_hsharz.core.auth.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "UserController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@RestController()
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        if(userDTO == null) {
            throw new IllegalArgumentException("userDTO must not be null.");
        }
        if(userService.isUserExisting(userDTO.getUserName())) {
            throw new IllegalArgumentException("userName already exists");
        }
        User user = convertToUser(userDTO);
        final User persistedUser = userService.persistUser(user);
        return ResponseEntity.ok(convertToUserDTO(persistedUser));
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPassword(user.getUserPassword());
        userDTO.setUserRole(user.getUserRole());
        return userDTO;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = userService.createUser();
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserRole(userDTO.getUserRole());
        return user;
    }

}
