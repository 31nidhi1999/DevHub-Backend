package com.devhub.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void send(String to, String subject, String body) {
		try {
			log.info("Preparing to send email to={}", to);
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setSubject(subject);
			mail.setText(body);
			javaMailSender.send(mail);
			log.info("Email sent successfully to={}", to);
		} catch (Exception e) {
			log.error("Failed to send email to={} subject={}. Error: {}", to, subject, e.getMessage(), e);
		}
	}
}
