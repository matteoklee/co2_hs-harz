package de.kleemann.co2_hsharz.api.auth;

import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.auth.UserRole;
import lombok.Data;
import lombok.NonNull;

/**
 * Class "UserDTO" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */

@Data
public class UserDTO {

    private long userId;
    private String userName;
    private String userPassword;
    private String userRole;

    //TODO unsauber
    public UserRole getUserRole() {
    	try {
    		UserRole role = UserRole.valueOf(this.userRole);
    		return role;
    	}
    	catch(Exception e) {
    		throw new CustomIllegalArgumentException("User Role not found");
    	}
    }
    
    public void setUserRole(@NonNull UserRole userRole) {
        this.userRole = userRole.toString();
    }
}
