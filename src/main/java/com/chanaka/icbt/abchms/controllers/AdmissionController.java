package com.chanaka.icbt.abchms.controllers;

import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.chanaka.icbt.abchms.custom.DataConverter;
import com.chanaka.icbt.abchms.custom.ResponseBuilder;
import com.chanaka.icbt.abchms.custom.ResponseType;
import com.chanaka.icbt.abchms.custom.Texts;
import com.chanaka.icbt.abchms.dtos.AdmissionGeneralDTO;
import com.chanaka.icbt.abchms.forms.AdmissionForm;
import com.chanaka.icbt.abchms.models.Admission;
import com.chanaka.icbt.abchms.services.AdmissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/admissions")
public class AdmissionController {

	@Autowired
	private AdmissionService admissionService;

	@GetMapping()
	public ResponseEntity<?> index() {
		Stream<Object> data = admissionService.index().stream()
				.map(admission -> DataConverter.convert(admission, AdmissionGeneralDTO.class));
		return ResponseBuilder.build("Admissions", ResponseType.FOUND, data);
	}

	@PostMapping()
	public ResponseEntity<?> store(@Valid @RequestBody AdmissionForm admissionForm) {
		Admission admission = (Admission) DataConverter.convert(admissionForm, Admission.class);
		Admission created = admissionService.store(admission);
		AdmissionGeneralDTO createdAdmission = (AdmissionGeneralDTO) DataConverter.convert(created,
				AdmissionGeneralDTO.class);
		return ResponseBuilder.build("Admission", ResponseType.CREATED, createdAdmission);
	}

	@PostMapping("/transfer/{uuid}")
	public ResponseEntity<?> transfer(@PathVariable String uuid, @Valid @RequestBody AdmissionForm admissionForm) {
		Admission admission = (Admission) DataConverter.convert(admissionForm, Admission.class);
		Admission created = admissionService.transfer(uuid, admission);
		AdmissionGeneralDTO createdAdmission = (AdmissionGeneralDTO) DataConverter.convert(created,
				AdmissionGeneralDTO.class);
		return ResponseBuilder.build(Texts.PATIENT_TRANSFERED, ResponseType.OK, createdAdmission);
	}

	@GetMapping(path = "/{uuid}")
	public ResponseEntity<?> show(@PathVariable String uuid) {
		Optional<Admission> admission = admissionService.find(uuid);
		if (admission.isEmpty()) {
			return ResponseBuilder.build("Admission", ResponseType.NOT_FOUND, null);
		}
		AdmissionGeneralDTO foundedAdmission = (AdmissionGeneralDTO) DataConverter.convert(admission,
				AdmissionGeneralDTO.class);
		return ResponseBuilder.build("Admission", ResponseType.FOUND, foundedAdmission);
	}

	@PutMapping(path = "/{uuid}")
	public ResponseEntity<?> update(@PathVariable String uuid, @Valid @RequestBody AdmissionForm admissionForm) {
		Admission admission = (Admission) DataConverter.convert(admissionForm, Admission.class);
		Admission updated = admissionService.update(uuid, admission);
		if (updated == null) {
			return ResponseBuilder.build("Admission", ResponseType.NOT_FOUND, null);
		}
		AdmissionGeneralDTO updatedAdmission = (AdmissionGeneralDTO) DataConverter.convert(admission,
				AdmissionGeneralDTO.class);
		return ResponseBuilder.build("Admission", ResponseType.UPDATED, updatedAdmission);
	}

	@PutMapping(path = "/discharge/{uuid}")
	public ResponseEntity<?> discharge(@PathVariable String uuid) {
		Admission discharged = admissionService.discharge(uuid);
		if (discharged != null) {
			AdmissionGeneralDTO dischargedAdmission = (AdmissionGeneralDTO) DataConverter.convert(discharged,
					AdmissionGeneralDTO.class);
			return ResponseBuilder.build(Texts.PATIENT_DISCHARGED, ResponseType.OK, dischargedAdmission);
		}
		return ResponseBuilder.build("Admission", ResponseType.NOT_FOUND, null);
	}

	@DeleteMapping(path = "/{uuid}")
	public ResponseEntity<?> destroy(@PathVariable String uuid) {
		boolean deleted = admissionService.delete(uuid);
		if (deleted) {
			return ResponseBuilder.build("Admission", ResponseType.DELETED, null);
		}
		return ResponseBuilder.build("Admission", ResponseType.NOT_FOUND, null);
	}

}
