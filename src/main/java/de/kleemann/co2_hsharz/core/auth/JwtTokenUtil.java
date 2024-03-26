package de.kleemann.co2_hsharz.core.auth;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Value("${auth.jwt.expiration}")
	private long tokenExpiration;

	private final SecretKey secretKey;
	private final MacAlgorithm signingAlgorithm;
	
	public JwtTokenUtil(final SecretKey secretKey, final MacAlgorithm signingAlgorithm) {
		this.secretKey = secretKey;
		this.signingAlgorithm = signingAlgorithm;
	}
	
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	public Date getExpirationDate(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(User user) {
		Claims claims = Jwts.claims()
							.subject(user.getUserName())
							.add("roles", user.getUserRole())
							.build();	
		
		return Jwts.builder()
				.claims(claims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + tokenExpiration*1000))
				.signWith(this.secretKey, this.signingAlgorithm)
				.compact();
	}
	
	public boolean validateToken(String token) {
		final String username = getUsername(token);
		
		return username != null && !isTokenExpired(token);
	}
	
	private Claims getAllClaims(String token) {
		JwtParser parser = Jwts.parser().verifyWith(this.secretKey).build();
		return parser.parseSignedClaims(token).getPayload();
	}
	
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDate(token);
		return expiration.before(new Date());
	}
}
