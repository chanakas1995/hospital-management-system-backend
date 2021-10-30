package com.chanaka.icbt.abchms.custom.communication.messages;

import lombok.Getter;

@Getter
public abstract class CommunicationMessage {
	protected String message;
	protected String title;
	protected String receiver;
}
