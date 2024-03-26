package de.kleemann.co2_hsharz.core.auth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.auth.UserEntity;
import de.kleemann.co2_hsharz.persistence.auth.UserPersistenceService;
import lombok.NonNull;

/**
 * Class "UserService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Service
public class UserService implements UserDetailsService {

    private final UserPersistenceService userPersistenceService;
    private final ConfigurableApplicationContext context;

    public UserService(final UserPersistenceService userPersistenceService, final ConfigurableApplicationContext context) {
        this.userPersistenceService = userPersistenceService;
        this.context = context;
    }

    /**
     * Loads the UserDetails for a specific username. <br>
     * Necessary for Spring Security to authenticate a user
     */
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        if(username.isBlank())
            throw new UsernameNotFoundException("username must not be blank.");
        
        UserEntity userEntity = userPersistenceService.findUserByUsername(username);
        if(userEntity == null)
        	throw new UsernameNotFoundException("User with name " + username + " not found");
        
        return org.springframework.security.core.userdetails.User
        		.builder()
        		.username(userEntity.getUserName())
        		.password(userEntity.getUserPassword())
        		.roles(userEntity.getUserRole().toString())
        		.build();
    }
    
	public Optional<User> findUser(String username) {
		UserEntity entity = userPersistenceService.findUserByUsername(username);
		if(entity == null)
			return Optional.empty();
		return Optional.of(new User(entity));
	}

    public List<User> findAllUsers() {
        return userPersistenceService.findAllUsers()
                .stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public boolean isUserExisting(String userName) {
        return findAllUsers()
        		.stream()
        		.anyMatch(user -> user.getUserName().equals(userName));
    }

    public User persistUser(@NonNull final User user) {
    	PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
    	//Encode password before saving!
    	user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
    	
        final UserEntity persistedUserEntity;
        try {
            persistedUserEntity = userPersistenceService.persistUser(user.getUserEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("Failed persisting a new user: " + exception.getLocalizedMessage());
        }
        return new User(persistedUserEntity);
    }

    public User createUser() {
        return new User(userPersistenceService.createUserEntity());
    }

    public User createUser(long id) {
        return new User(userPersistenceService.createUserEntity(id));
    }
}
