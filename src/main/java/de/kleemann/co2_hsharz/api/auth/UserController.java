package de.kleemann.co2_hsharz.api.auth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.kleemann.co2_hsharz.core.auth.JwtTokenUtil;
import de.kleemann.co2_hsharz.core.auth.User;
import de.kleemann.co2_hsharz.core.auth.UserService;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;

/**
 * Class "UserController" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@RestController()
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;
    
    public UserController(UserService userService, final AuthenticationManager authenticationManager, final JwtTokenUtil tokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDTO request) {
    	try {
    		System.out.println("Login Request: " + request.getUsername() + ", " + request.getPassword());
    		authenticate(request.getUsername(), request.getPassword());
    	}
    	catch(DisabledException e) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is disabled.");
    	}
    	catch(BadCredentialsException e) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    	}
    	
    	final Optional<User> optionalUser = userService.findUser(request.getUsername());
    	if(optionalUser.isEmpty())
    		return ResponseEntity.internalServerError().body("Exception in login. Authenticated user could not be retrieved");
    	final String token = tokenUtil.generateToken(optionalUser.get());
    	return ResponseEntity.ok(token);
    }
    
    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException{
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if(userDTO == null) {
            throw new CustomIllegalArgumentException("userDTO must not be null.");
        }
        if(userService.isUserExisting(userDTO.getUserName())) {
            throw new CustomIllegalArgumentException("userName already exists");
        }
        User user;
        try {
        	user = convertToUser(userDTO);
        }
        catch(CustomIllegalArgumentException e) {
        	return ResponseEntity.badRequest().body("Invalid User Credentials or Role.");
        }
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

    private User convertToUser(UserDTO userDTO) throws CustomIllegalArgumentException {
        User user = userService.createUser();
        user.setUserName(userDTO.getUserName());
        user.setUserPassword(userDTO.getUserPassword());
        user.setUserRole(userDTO.getUserRole());
        return user;
    }

}
