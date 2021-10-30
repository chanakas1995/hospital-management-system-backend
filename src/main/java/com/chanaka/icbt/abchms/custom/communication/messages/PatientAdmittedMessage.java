package com.chanaka.icbt.abchms.custom.communication.messages;

import com.chanaka.icbt.abchms.custom.Texts;
import com.chanaka.icbt.abchms.custom.communication.MessageTypes;
import com.chanaka.icbt.abchms.models.Admission;

public class PatientAdmittedMessage extends CommunicationMessage {
	public PatientAdmittedMessage(Admission admission, MessageTypes messageType) {
		this.title = Texts.PATIENT_ADMITTED_TO_HOSPITAL_TITLE;
		String message = "";
		switch (messageType) {
		case SMS:
			message = Texts.PATIENT_ADMITTED_TO_HOSPITAL_SMS;
			this.receiver = admission.getPatient().getContactPersonMobile();
			break;
		case EMAIL:
			message = Texts.PATIENT_ADMITTED_TO_HOSPITAL_EMAIL;
			this.receiver = admission.getPatient().getContactPersonEmail();
			break;
		}
		this.message = message.replace("{name}", admission.getPatient().getName())
				.replace("{contactPersonName}", admission.getPatient().getContactPersonName())
				.replace("{branch}", admission.getBranch().getBranchName())
				.replace("{ward}", admission.getWard().getWardName())
				.replace("{number}", admission.getBranch().getTelephone());
	}
}
