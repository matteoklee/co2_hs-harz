package de.kleemann.co2_hsharz.core.auth;

import de.kleemann.co2_hsharz.persistence.UserEntity;
import de.kleemann.co2_hsharz.persistence.UserPersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class "UserService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Service
public class UserService {

    private final UserPersistenceService userPersistenceService;

    public UserService(UserPersistenceService userPersistenceService) {
        this.userPersistenceService = userPersistenceService;
    }

    /*
    public UserDetails loadUserByUsername(String username) {
        if(username.isBlank() || username.isEmpty()) {
            throw new IllegalArgumentException("username may not be null.");
        }
        return userPersistenceService.loadUserByUsername(username);
    }
    */

    public List<User> findAllUsers() {
        return userPersistenceService.findAllUsers()
                .stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    public User persistUser(final User user) {
        if(user == null) {
            throw new IllegalArgumentException("user must not be null.");
        }
        final UserEntity persistedUserEntity;
        try {
            persistedUserEntity = userPersistenceService.persistUser(user.getUserEntity());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
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
