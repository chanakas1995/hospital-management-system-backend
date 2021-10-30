package com.chanaka.icbt.abchms;

import java.util.Properties;

import com.chanaka.icbt.abchms.custom.communication.clients.EmailClient;
import com.chanaka.icbt.abchms.custom.communication.clients.SmsClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AbchmsApplication {
	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(AbchmsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowedMethods("GET", "POST", "PUT",
						"DELETE");
			}
		};
	}

	@Bean
	public SmsClient smsClient() {
		SmsClient.setup(environment.getProperty("textit.username"), environment.getProperty("textit.password"),
				Boolean.valueOf(environment.getProperty("sms.isOn")));
		return new SmsClient();
	}

	@Bean
	public EmailClient emailClient() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));

		mailSender.setUsername(environment.getProperty("spring.mail.username"));
		mailSender.setPassword(environment.getProperty("spring.mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");

		EmailClient.setup(mailSender, Boolean.valueOf(environment.getProperty("email.isOn")));
		return new EmailClient();
	}

}
