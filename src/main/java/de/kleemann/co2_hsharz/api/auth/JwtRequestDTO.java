package de.kleemann.co2_hsharz.api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDTO {

	private String username;
	private String password;
}
