package com.chanaka.icbt.abchms.forms;

import javax.validation.constraints.NotNull;

import com.chanaka.icbt.abchms.models.Branch;
import com.chanaka.icbt.abchms.models.Patient;
import com.chanaka.icbt.abchms.models.Ward;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdmissionForm extends RequestForm {

	@JsonProperty("notes")
	private String notes;

	@NotNull(message = "Branch" + REQUIRED)
	@JsonProperty("branch")
	private Branch branch;

	@NotNull(message = "Ward" + REQUIRED)
	@JsonProperty("ward")
	private Ward ward;

	@NotNull(message = "Patient" + REQUIRED)
	@JsonProperty("patient")
	private Patient patient;

	public AdmissionForm() {
		this.entityName = "Admission";
	}

}
