package de.kleemann.co2_hsharz.core.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.core.exceptions.CustomRuntimeException;
import de.kleemann.co2_hsharz.persistence.auth.UserEntity;
import de.kleemann.co2_hsharz.persistence.auth.UserPersistenceService;
import lombok.NonNull;

/**
 * This Service provides core layer functionality to create and read {@link User}s
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Service
public class UserService {

	/**
	 * {@link UserPersistenceService}
	 */
    private final UserPersistenceService userPersistenceService;

    /**
     * Constructs a {@link UserService} using an {@link UserPersistenceService}
     * @param userPersistenceService - {@link UserPersistenceService}
     */
    public UserService(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    /**
     * Loads Spring {@link UserDetails} of a {@link User} with this username
     * @param username - {@link String} username
     * @return {@link UserDetails} for this {@link User}
     * @throws CustomIllegalArgumentException if username is empty or blank
     */
    public UserDetails loadUserByUsername(@NonNull String username) {
        if(username.isBlank()) {
            throw new CustomIllegalArgumentException("username may not be null.");
        }
        return userPersistenceService.loadUserByUsername(username);
    }

    /**
     * Returns a {@link List} of all {@link User}s in the database
     * @return {@link List} of all {@link User}s
     */
    public List<User> findAllUsers() {
        return userPersistenceService.findAllUsers()
                .stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a {@link User} exists
     * @param userName - {@link String} name of the {@link User}
     * @return true, if the user with this name exists, false else
     */
    public boolean isUserExisting(@NonNull String userName) {
        return findAllUsers().stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    /**
     * Persists a {@link User} in the database
     * @param user - {@link User} to persist
     * @return persisted {@link User}
     * @throws CustomIllegalArgumentException if {@code user} is null or {@link CustomRuntimeException} if an error occurs
     */
    public User persistUser(@NonNull final User user) {
        if(user == null) {
            throw new CustomIllegalArgumentException("user must not be null.");
        }
        final UserEntity persistedUserEntity;
        try {
            persistedUserEntity = userPersistenceService.persistUser(user.getUserEntity());
        } catch (Exception exception) {
            throw new CustomRuntimeException("error in persistUser()");
        }
        return new User(persistedUserEntity);
    }

    /**
     * Creates a new {@link User}
     * @return created {@link User}
     */
    public User createUser() {
        return new User(userPersistenceService.createUserEntity());
    }

    /**
     * Creates a new {@link User} with a given Id
     * @param id - {@code long} Id for this {@link User}
     * @return created {@link User}
     */
    public User createUser(long id) {
        return new User(userPersistenceService.createUserEntity(id));
    }

}
