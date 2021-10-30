package com.chanaka.icbt.abchms.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

	public String getDischargedDate() {
		return dischargedDate == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(dischargedDate);
	}

	public double getBillAmount() {

		long diff = (dischargedDate == null ? new Date().getTime() : dischargedDate.getTime())
				- admissionDate.getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		return days == 0 ? ward.getPricePerDay() : days * ward.getPricePerDay();
	}
}
