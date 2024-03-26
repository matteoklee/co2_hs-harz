package de.kleemann.co2_hsharz.api.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.kleemann.co2_hsharz.core.auth.JwtTokenFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class "SecurityConfiguration" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 16.10.2023
 */
//@EnableWebSecurity
@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

//    AuthenticationManager authenticationManager;
    private final HandlerInterceptor apiRequestInterceptor;

    @Value("${custom.user.username}")
    private String userUsername;

    @Value("${custom.user.password}")
    private String userPassword;

    @Value("${custom.admin.username}")
    private String adminUsername;

    @Value("${custom.admin.password}")
    private String adminPassword;

    public SecurityConfiguration(final HandlerInterceptor apiRequestInterceptor) {
        this.apiRequestInterceptor = apiRequestInterceptor;
    }
    
    /**
     * Adds the {@link ApiRequestInterceptor} as a {@link HandlerInterceptor} to the {@link InterceptorRegistry}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRequestInterceptor);
    }

    /**
     * Defines a PasswordEncoder Bean. <br>
     * Uses the {@link DelegatingPasswordEncoder}
     * @return {@link PasswordEncoder}
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean(name = "signatureAlgorithm")
    MacAlgorithm signatureAlgorithm() {
    	return Jwts.SIG.HS512;
    }
    
    @Bean
    @DependsOn("signatureAlgorithm")
    SecretKey secretKey(MacAlgorithm signatureAlgorithm) {
    	return signatureAlgorithm.key().build();
    }
    
    @Bean
    @DependsOn("userService")
    AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userService) throws Exception {
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	authenticationManagerBuilder.userDetailsService(userService);
    	return authenticationManagerBuilder.build();
    }
    
    @Bean
    @DependsOn("authenticationManager")
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager, JwtTokenFilter jwtTokenFilter) throws Exception {   	
    	http.csrf(customizer -> customizer.disable())	//Cross Site Request Forgery (CSRF) Protection is enabled by default. Disable only for developing purposes
    		.cors(Customizer.withDefaults())	//Cross Origin Resource Sharing
    		.sessionManagement(configurer -> {
    			configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    		})
    		.exceptionHandling(configurer -> {
    			configurer.authenticationEntryPoint((request, response, exception) -> {
    				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    			});
    		})
    		.authenticationManager(authenticationManager)
    		.authorizeHttpRequests(configurer -> {
    			configurer
    				.requestMatchers("/api/private/**").authenticated()
    				.requestMatchers("/**").permitAll();
    		})
    		.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }
}
