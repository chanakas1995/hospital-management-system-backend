package com.chanaka.icbt.abchms.custom.communication.clients;

import com.chanaka.icbt.abchms.custom.communication.messages.CommunicationMessage;

public interface CommunicationClient {
	public void sendMessage(CommunicationMessage communicationMessage);
}
