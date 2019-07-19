package com.ebassauto.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {
	
		
	@Autowired
    private JavaMailSender javaMailSender;
	
	@GetMapping("/getDateTime")
	private Date getLocalDt() {
		
//		SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("prdrek@gmail.com");
//
//        msg.setSubject("Testing from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
		
		
		return new Date();
	}
}
