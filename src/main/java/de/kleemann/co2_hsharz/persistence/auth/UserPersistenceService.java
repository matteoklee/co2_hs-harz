package de.kleemann.co2_hsharz.persistence.auth;

import de.kleemann.co2_hsharz.core.exceptions.CustomEntityExistsException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class "UserPersistenceService" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
@Service
public class UserPersistenceService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserPersistenceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("username does not exists yet.");
        }
        return new User(userEntity.getUserName(), userEntity.getUserPassword(), getAuthorities(userEntity));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserRole()));
        return authorities;
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

    private UserEntity saveUser(final UserEntity userEntity) {
        try {
            return userRepository.save(userEntity);
        }
        catch (EntityExistsException exception) {
            throw new CustomEntityExistsException("user already exists.");
        }
    }

    public UserEntity persistUser(final UserEntity userEntity) {
        if (userEntity == null) {
            throw new CustomIllegalArgumentException("user must not be null.");
        }
        return saveUser(userEntity);
    }


}
