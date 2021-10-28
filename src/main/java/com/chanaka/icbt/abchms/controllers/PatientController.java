package com.chanaka.icbt.abchms.controllers;

import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.chanaka.icbt.abchms.custom.AlreadyInUseException;
import com.chanaka.icbt.abchms.custom.DataConverter;
import com.chanaka.icbt.abchms.custom.ResponseBuilder;
import com.chanaka.icbt.abchms.custom.ResponseType;
import com.chanaka.icbt.abchms.dtos.PatientGeneralDTO;
import com.chanaka.icbt.abchms.dtos.PatientOptionDTO;
import com.chanaka.icbt.abchms.forms.PatientForm;
import com.chanaka.icbt.abchms.models.Patient;
import com.chanaka.icbt.abchms.services.PatientService;

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
@RequestMapping(path = "/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping()
	public ResponseEntity<?> index() {
		Stream<Object> data = patientService.index().stream()
				.map(patient -> DataConverter.convert(patient, PatientGeneralDTO.class));
		return ResponseBuilder.build("Patients", ResponseType.FOUND, data);
	}

	@GetMapping(path = "/options")
	public ResponseEntity<?> getOptions() {
		Stream<Object> data = patientService.index().stream()
				.map(patient -> DataConverter.convert(patient, PatientOptionDTO.class));
		return ResponseBuilder.build("Patients", ResponseType.FOUND, data);
	}

	@PostMapping()
	public ResponseEntity<?> store(@Valid @RequestBody PatientForm patientForm) {
		try {
			Patient patient = (Patient) DataConverter.convert(patientForm, Patient.class);
			Patient created = patientService.store(patient);
			PatientGeneralDTO createdPatient = (PatientGeneralDTO) DataConverter.convert(created,
					PatientGeneralDTO.class);
			return ResponseBuilder.build("Patient", ResponseType.CREATED, createdPatient);
		} catch (AlreadyInUseException e) {
			return e.getJsonResponse();
		}
	}

	@GetMapping(path = "/{uuid}")
	public ResponseEntity<?> show(@PathVariable String uuid) {
		Optional<Patient> patient = patientService.find(uuid);
		if (patient.isEmpty()) {
			return ResponseBuilder.build("Patient", ResponseType.NOT_FOUND, null);
		}
		PatientGeneralDTO foundedPatient = (PatientGeneralDTO) DataConverter.convert(patient, PatientGeneralDTO.class);
		return ResponseBuilder.build("Patient", ResponseType.FOUND, foundedPatient);
	}

	@PutMapping(path = "/{uuid}")
	public ResponseEntity<?> update(@PathVariable String uuid, @Valid @RequestBody PatientForm patientForm) {
		try {
			Patient patient = (Patient) DataConverter.convert(patientForm, Patient.class);
			Patient updated = patientService.update(uuid, patient);
			if (updated == null) {
				return ResponseBuilder.build("Patient", ResponseType.NOT_FOUND, null);
			}
			PatientGeneralDTO updatedPatient = (PatientGeneralDTO) DataConverter.convert(patient,
					PatientGeneralDTO.class);
			return ResponseBuilder.build("Patient", ResponseType.UPDATED, updatedPatient);
		} catch (AlreadyInUseException e) {
			return e.getJsonResponse();
		}
	}

	@DeleteMapping(path = "/{uuid}")
	public ResponseEntity<?> destroy(@PathVariable String uuid) {
		boolean deleted = patientService.delete(uuid);
		if (deleted) {
			return ResponseBuilder.build("Patient", ResponseType.DELETED, null);
		}
		return ResponseBuilder.build("Patient", ResponseType.NOT_FOUND, null);
	}

}
