package com.ebassauto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ebassauto.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


public class Jwtauthfilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Fetching the authorization header from the request.
		String authenticationHeader= request.getHeader(Iconstants.HEADER);

		try {
			SecurityContext context= SecurityContextHolder.getContext();

			if(authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {

				final String bearerTkn= authenticationHeader.replaceAll(Iconstants.BEARER_TOKEN, "");
				System.out.println("Following token is received from the protected url= "+ bearerTkn);

				try {
					// Parsing the jwt token.
					Jws<Claims> claims = Jwts.parser().requireIssuer(Iconstants.ISSUER).setSigningKey(Iconstants.SECRET_KEY).parseClaimsJws(bearerTkn);

					// Obtaining the claims from the parsed jwt token.
					String user= (String) claims.getBody().get("usr");
					String roles= (String) claims.getBody().get("rol");

					// Creating the list of granted-authorities for the received roles.
					List<GrantedAuthority> authority= new ArrayList<GrantedAuthority>();
					for(String role: roles.split(","))
						authority.add(new SimpleGrantedAuthority(role));

					// Creating an authentication object using the claims.
					if(jwtTokenUtil.validateToken(bearerTkn)) {
						Myauthtoken authenticationTkn= new Myauthtoken(user, null, authority);
						// Storing the authentication object in the security context.
						context.setAuthentication(authenticationTkn);
					}
					
				} catch (SignatureException e) {
					throw new ServletException("Invalid token.");
				}catch (IllegalArgumentException e) {
					throw new ServletException("Unable to get JWT Token.");
					//System.out.println("Unable to get JWT Token");
				}catch (ExpiredJwtException e) {
					throw new ServletException("JWT Token has expired.");
					//System.out.println("JWT Token has expired");
				}
			}

			filterChain.doFilter(request, response);
			context.setAuthentication(null);
		} catch(AuthenticationException ex) {
			throw new ServletException("Authentication exception.");
		}		
		
	}	
	
}