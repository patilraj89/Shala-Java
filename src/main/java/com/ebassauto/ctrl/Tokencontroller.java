package com.ebassauto.ctrl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebassauto.Iconstants;
import com.ebassauto.model.LoginDetails;
import com.ebassauto.model.User;
import com.ebassauto.service.ILoginDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin
@RestController
public class Tokencontroller {
	
	@Autowired
	ILoginDetailsService loginService;
	
	public static final long JWT_TOKEN_VALIDITY = 12*60*60;
	@PostMapping("/token")
	public ResponseEntity<String> getToken(@RequestBody User login) throws ServletException {

		System.out.println("Uname="+login.getUsername());
		String jwttoken = "";

		if(login.getUsername().isEmpty() || login.getPassword().isEmpty())
			return new ResponseEntity<String>("Username or password cannot be empty.", HttpStatus.BAD_REQUEST);

		String name = login.getUsername(), 
				password = login.getPassword();
		List<LoginDetails> logDetails = loginService.getLoginDetails(login.getUsername(), login.getPassword());
		
		//if(!(name.equalsIgnoreCase("Administrator") && password.equalsIgnoreCase("admin123"))) {
		if(logDetails.isEmpty()) {
			return new ResponseEntity<String>("Invalid credentials. Please check the username and password.", HttpStatus.UNAUTHORIZED);
		}else {
			// Creating JWT using the user credentials.
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("usr", login.getUsername());
			claims.put("sub", "Authentication token");
			claims.put("iss", Iconstants.ISSUER);
			claims.put("rol", "Administrator");
			claims.put("iat", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			jwttoken = Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, Iconstants.SECRET_KEY).compact();
			//System.out.println("Returning the following token to the user= "+ jwttoken);
		}

		return new ResponseEntity<String>(jwttoken, HttpStatus.OK);
	}
}