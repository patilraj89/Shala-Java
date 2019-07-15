package com.ebassauto.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

public class SentEmail {
//	@Autowired
//    private JavaMailSender javaMailSender;
//	public static void main (String args[]) {
//		SentEmail obj = new SentEmail();
//		obj.sentEmail("abc");
//	}
	
	public void sentEmail(String mailAddr) {
//		try {
//			Properties props = new Properties();
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp.gmail.com");
//	        props.put("mail.smtp.port", "587");
//	        props.put("mail.debug", "true");
//	        Session session = Session.getDefaultInstance(props);
//	        MimeMessage message = new MimeMessage(session);
//	        message.setFrom(new InternetAddress("prdrek@gmail.com"));
//	        message.setRecipient(RecipientType.TO, new InternetAddress("patilraj198991@gmail.com"));
//	        message.setSubject("Notification");
//	        message.setText("Successful!", "UTF-8"); // as "text/plain"
//	        message.setSentDate(new Date());
//	        Transport.send(message);
//			return "Success";
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return "Success";
//		SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("patilraj198991@gmail.com");
//
//        msg.setSubject("Testing from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
	}

}
