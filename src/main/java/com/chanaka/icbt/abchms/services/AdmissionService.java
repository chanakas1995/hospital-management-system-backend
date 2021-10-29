package com.chanaka.icbt.abchms.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.chanaka.icbt.abchms.models.Admission;
import com.chanaka.icbt.abchms.repositories.AdmissionRepository;
import com.chanaka.icbt.abchms.repositories.BranchRepository;
import com.chanaka.icbt.abchms.repositories.PatientRepository;
import com.chanaka.icbt.abchms.repositories.WardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	WardRepository wardRepository;

	@Autowired
	PatientRepository patientRepository;

	public List<Admission> index() {
		return admissionRepository.findAll();
	}

	public Admission store(Admission admission) {
		admission.generateUuid();
		admission.setAdmissionDate(new Date());
		return admissionRepository.save(bindRelationships(admission));
	}

	public Optional<Admission> find(String uuid) {
		return admissionRepository.findByUuid(uuid);
	}

	public Admission transfer(String uuid, Admission admission) {
		Optional<Admission> currentAdmission = admissionRepository.findByUuid(uuid);
		if (currentAdmission.isPresent()) {
			currentAdmission.get().setDischargedDate(new Date());
			admission.setAdmissionDate(new Date());
			admission.generateUuid();
			admissionRepository.save(bindRelationships(admission));
			return admission;
		}
		return null;
	}

	public Admission update(String uuid, Admission admission) {
		Optional<Admission> currentAdmission = admissionRepository.findByUuid(uuid);
		if (currentAdmission.isPresent()) {
			admission.setId(currentAdmission.get().getId());
			admission.setAdmissionDate(currentAdmission.get().getAdmissionDate());
			admission.setUuid(uuid);
			admissionRepository.save(bindRelationships(admission));
			return admission;
		}
		return null;
	}

	public Admission discharge(String uuid) {
		Optional<Admission> admission = admissionRepository.findByUuid(uuid);
		if (admission.isPresent()) {
			Admission dischargedAdmission = admission.get();
			dischargedAdmission.setDischargedDate(new Date());
			admissionRepository.save(bindRelationships(dischargedAdmission));
			return dischargedAdmission;
		}
		return null;
	}

	public boolean delete(String uuid) {
		Optional<Admission> admission = admissionRepository.findByUuid(uuid);
		if (admission.isPresent()) {
			admissionRepository.deleteById(admission.get().getId());
			return true;
		}
		return false;
	}

	private Admission bindRelationships(Admission admission) {
		admission.setBranch(branchRepository.findById(admission.getBranch().getId()).get());
		admission.setPatient(patientRepository.findById(admission.getPatient().getId()).get());
		admission.setWard(wardRepository.findById(admission.getWard().getId()).get());
		return admission;
	}

}
