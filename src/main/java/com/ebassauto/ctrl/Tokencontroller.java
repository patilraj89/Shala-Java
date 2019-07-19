package com.ebassauto.ctrl;

import java.util.Arrays;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebassauto.model.User;
import com.ebassauto.pojo.UserAuthPojo;
import com.ebassauto.service.ILoginDetailsService;
import com.ebassauto.util.JwtTokenUtil;

@CrossOrigin()
@RestController
public class Tokencontroller {
	
	@Autowired
	ILoginDetailsService loginService;	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	public static final String RES_MESAGE = "User Not Authorised.";
	public static final String PASS_EMPT_MESAGE = "Username or password cannot be empty.";
	
	@PostMapping("/token")
	public ResponseEntity<UserAuthPojo> getToken(@RequestBody User login) throws ServletException {
		UserAuthPojo userAuthPojo = new UserAuthPojo();
		System.out.println("Uname="+login.getUsername());
		String jwttoken = "";

		if(login.getUsername().isEmpty() || login.getPassword().isEmpty()) {
			userAuthPojo.setAuthenticated(false);
			userAuthPojo.setResponseMessage(PASS_EMPT_MESAGE);
			return new ResponseEntity<UserAuthPojo>(userAuthPojo, HttpStatus.BAD_REQUEST);
		}
		Object[] logDetails = loginService.getLoginDetails(login.getUsername(), login.getPassword());		
		
		if(logDetails.length<=0) {
			userAuthPojo.setResponseMessage(RES_MESAGE);
			return new ResponseEntity<UserAuthPojo>(userAuthPojo, HttpStatus.UNAUTHORIZED);
		}else {
			
			jwttoken=jwtTokenUtil.generateJwtToken(login);
			userAuthPojo.setAuthenticated(true);
			String[] adminStr = Arrays.copyOf(logDetails, logDetails.length,String[].class);
			userAuthPojo.setRole(Arrays.asList(adminStr));
			userAuthPojo.setToken(jwttoken);
			userAuthPojo.setResponseMessage("Success");			
			//System.out.println("Returning the following token to the user= "+ jwttoken);
		}

		return new ResponseEntity<UserAuthPojo>(userAuthPojo, HttpStatus.OK);
	}
}