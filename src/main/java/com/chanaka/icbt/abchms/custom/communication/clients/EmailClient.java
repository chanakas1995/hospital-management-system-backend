package com.chanaka.icbt.abchms.custom.communication.clients;

import com.chanaka.icbt.abchms.custom.communication.messages.CommunicationMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailClient implements CommunicationClient {
	private static JavaMailSender mailSender;
	private static boolean isOn;

	private static EmailClient smsClient = new EmailClient();

	public static void setup(JavaMailSender javaMailSender, boolean isOn) {
		EmailClient.mailSender = javaMailSender;
		EmailClient.isOn = isOn;
	}

	public static EmailClient getInstance() {
		return smsClient;
	}

	@Override
	public void sendMessage(CommunicationMessage communicationMessage) {
		if (isOn) {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(communicationMessage.getReceiver());
			msg.setSubject(communicationMessage.getTitle());
			msg.setText(communicationMessage.getMessage());
			mailSender.send(msg);
		}
	}

}
