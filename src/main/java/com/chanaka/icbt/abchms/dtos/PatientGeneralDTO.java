package com.chanaka.icbt.abchms.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class PatientGeneralDTO {
	private String uuid;
	private String name;
	private String nic;
	private String email;
	private String mobile;
	private String gender;
	private String bloodGroup;
	private Date dateOfBirth;
	private String address;
	private String contactPersonName;
	private String contactPersonMobile;
	private String contactPersonEmail;

	public String getDateOfBirth() {
		return new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
	}
}
