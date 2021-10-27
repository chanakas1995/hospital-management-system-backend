package com.chanaka.icbt.abchms.forms;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.chanaka.icbt.abchms.custom.validators.interfaces.Nic;
import com.chanaka.icbt.abchms.custom.validators.interfaces.PastDateOrEqual;
import com.chanaka.icbt.abchms.custom.validators.interfaces.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientForm extends RequestForm {

	public PatientForm() {
		this.entityName = "Patient";
	}

	@NotEmpty(message = "Name" + REQUIRED)
	@JsonProperty("name")
	private String name;

	@NotEmpty(message = "NIC is" + REQUIRED)
	@Nic(message = "NIC" + INVALID)
	@JsonProperty("nic")
	private String nic;

	@Email(message = "Email" + INVALID)
	@JsonProperty("email")
	private String email;

	@PhoneNumber(message = "Mobile" + INVALID)
	@JsonProperty("mobile")
	private String mobile;

	@NotNull(message = "Gender" + REQUIRED)
	@JsonProperty("gender")
	private String gender;

	@NotNull(message = "Blood group" + REQUIRED)
	@JsonProperty("bloodGroup")
	private String bloodGroup;

	@NotNull(message = "Date of birth" + REQUIRED)
	@PastDateOrEqual(message = "Date of birth" + PAST_DATE_OR_EQUAL)
	@JsonProperty("dateOfBirth")
	private Date dateOfBirth;

	@NotEmpty(message = "Address" + REQUIRED)
	@JsonProperty("address")
	private String address;

	@NotEmpty(message = "Contact person name" + REQUIRED)
	@JsonProperty("contactPersonName")
	private String contactPersonName;

	@PhoneNumber(message = "Contact person mobile" + INVALID)
	@JsonProperty("contactPersonMobile")
	private String contactPersonMobile;

	@JsonProperty("contactPersonEmail")
	@Email(message = "Email" + INVALID)
	private String contactPersonEmail;

}
