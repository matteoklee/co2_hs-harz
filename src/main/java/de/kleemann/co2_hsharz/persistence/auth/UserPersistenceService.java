package de.kleemann.co2_hsharz.persistence.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import jakarta.persistence.EntityExistsException;
import lombok.NonNull;

/**
 * Class "UserPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Service
public class UserPersistenceService {

    private final UserRepository userRepository;

    public UserPersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	public UserEntity findUserByUsername(@NonNull String username) {
		return userRepository.findByUserName(username);
	}

    public List<UserEntity> findAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public UserEntity createUserEntity() {
        return new UserEntity();
    }

    public UserEntity createUserEntity(long id) {
        return new UserEntity(id);
    }

    private UserEntity saveUser(@NonNull final UserEntity userEntity) {
        try {
            return userRepository.save(userEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("user already exists.");
        }
    }

    public UserEntity persistUser(@NonNull final UserEntity userEntity) {
        return saveUser(userEntity);
    }
}
