package com.ebassauto.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.ebassauto.Iconstants;
import com.ebassauto.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final long JWT_TOKEN_VALIDITY = 9*60*60;
	
	public Boolean validateToken(String token) {
		return (!isTokenExpired(token));
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		System.out.println("Token Date="+expiration);
		System.out.println("New Date="+new Date());
		System.out.println("Bool="+expiration.before(new Date()));
		return expiration.before(new Date());
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().requireIssuer(Iconstants.ISSUER).setSigningKey(Iconstants.SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public String generateJwtToken(User login) {
		String jwttoken = "";
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("usr", login.getUsername());
		claims.put("sub", "Authentication token");
		claims.put("iss", Iconstants.ISSUER);
		claims.put("rol", "Administrator");
		claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		jwttoken = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
		return jwttoken;
	}

}
