package de.kleemann.co2_hsharz.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	
	/**
	 * Constructs a new {@link PasswordEncoder}. In this case a {@link DelegatingPasswordEncoder} <br>
	 * to be used to encode user passwords
	 * @return {@link PasswordEncoder}
	 */
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
