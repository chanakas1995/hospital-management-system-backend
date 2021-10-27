package com.chanaka.icbt.abchms.services;

import java.util.List;
import java.util.Optional;

import com.chanaka.icbt.abchms.custom.AlreadyInUseException;
import com.chanaka.icbt.abchms.models.Patient;
import com.chanaka.icbt.abchms.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	PatientRepository patientRepository;

	public List<Patient> index() {
		return patientRepository.findAll();
	}

	public Patient store(Patient patient) throws AlreadyInUseException {
		Optional<Patient> findByNic = patientRepository.findByNic(patient.getNic());
		if (findByNic.isPresent() && !findByNic.get().getNic().isEmpty()) {
			throw new AlreadyInUseException("Patient", "Nic");
		}
		patient.generateUuid();
		return patientRepository.save(patient);
	}

	public Optional<Patient> find(String uuid) {
		return patientRepository.findByUuid(uuid);
	}

	public Patient update(String uuid, Patient patient) throws AlreadyInUseException {
		Optional<Patient> findByNic = patientRepository.findByNic(patient.getNic());
		if (findByNic.isPresent() && !findByNic.get().getNic().isEmpty() && !findByNic.get().getUuid().equals(uuid)) {
			throw new AlreadyInUseException("Patient", "Nic");
		}
		Optional<Patient> currentPatient = patientRepository.findByUuid(uuid);
		if (currentPatient.isPresent()) {
			patient.setId(currentPatient.get().getId());
			patient.setUuid(uuid);
			patientRepository.save(patient);
			return patient;
		}
		return null;
	}

	public boolean delete(String uuid) {
		Optional<Patient> patient = patientRepository.findByUuid(uuid);
		if (patient.isPresent()) {
			patientRepository.deleteById(patient.get().getId());
			return true;
		}
		return false;
	}

}
