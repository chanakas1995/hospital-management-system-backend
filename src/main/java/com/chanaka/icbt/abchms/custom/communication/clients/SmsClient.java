package com.chanaka.icbt.abchms.custom.communication.clients;

import com.chanaka.icbt.abchms.custom.communication.messages.CommunicationMessage;

import org.springframework.web.client.RestTemplate;

public class SmsClient implements CommunicationClient {

	private static SmsClient smsClient = new SmsClient();
	private static String username;
	private static String password;
	private static boolean isOn;

	public static void setup(String username, String password, boolean isOn) {
		SmsClient.username = username;
		SmsClient.password = password;
		SmsClient.isOn = isOn;
	}

	public static SmsClient getInstance() {
		return smsClient;
	}

	@Override
	public void sendMessage(CommunicationMessage communicationMessage) {
		if (isOn) {
			new RestTemplate().getForObject("http://www.textit.biz/sendmsg/index.php?id=" + username + "&password="
					+ password + "&text=" + communicationMessage.getMessage().replaceAll(" ", "%20") + "&to="
					+ communicationMessage.getReceiver() + "", String.class);
		}
	}
}
