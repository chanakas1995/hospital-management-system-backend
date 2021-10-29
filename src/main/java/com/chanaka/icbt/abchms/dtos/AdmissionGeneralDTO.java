package com.chanaka.icbt.abchms.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdmissionGeneralDTO {

	private Integer id;
	private String uuid;
	private String notes;
	private Date admissionDate;
	private Date dischargedDate;
	private BranchOptionDTO branch;
	private WardOptionDTO ward;
	private PatientOptionDTO patient;

	public String getAdmissionDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(admissionDate);
	}

	public String getdischargedDate() {
		return dischargedDate == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(dischargedDate);
	}
}
