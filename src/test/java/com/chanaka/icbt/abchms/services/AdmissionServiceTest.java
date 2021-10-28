package com.chanaka.icbt.abchms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.chanaka.icbt.abchms.AbchmsApplication;
import com.chanaka.icbt.abchms.custom.AlreadyInUseException;
import com.chanaka.icbt.abchms.models.Admission;
import com.chanaka.icbt.abchms.models.Branch;
import com.chanaka.icbt.abchms.models.Patient;
import com.chanaka.icbt.abchms.models.Ward;
import com.chanaka.icbt.abchms.repositories.BranchRepository;
import com.chanaka.icbt.abchms.repositories.PatientRepository;
import com.chanaka.icbt.abchms.repositories.WardRepository;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AbchmsApplication.class)
public class AdmissionServiceTest {

	@Autowired
	private AdmissionService admissionService;

	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private PatientRepository patientRepository;

	Faker faker = new Faker();

	@Test
	void testIndex() throws AlreadyInUseException {
		Admission admission1 = generateAdmission();
		Admission admission2 = generateAdmission();
		admissionService.store(admission1);
		admissionService.store(admission2);
		List<Admission> receivedAdmissionsList = admissionService.index();
		assertTrue(receivedAdmissionsList.contains(admission1));
		assertTrue(receivedAdmissionsList.contains(admission2));
	}

	@Test
	void testFind() throws AlreadyInUseException {
		Admission admission = generateAdmission();
		Admission createdAdmission = admissionService.store(admission);
		assertEquals(createdAdmission, admission);
	}

	@Test
	void testStore() throws AlreadyInUseException {
		Admission admission = generateAdmission();
		Admission createdAdmission = admissionService.store(admission);
		assertEquals(createdAdmission, admission);
	}

	@Test
	void testUpdate() throws AlreadyInUseException {
		Admission admission = generateAdmission();
		Admission createdAdmission = admissionService.store(admission);
		createdAdmission.setNotes(faker.lorem().sentence());
		Admission updatedAdmission = admissionService.update(createdAdmission.getUuid(), createdAdmission);
		assertEquals(createdAdmission, updatedAdmission);
	}

	@Test
	void testDelete() throws AlreadyInUseException {
		Admission admission = generateAdmission();
		Admission createdAdmission = admissionService.store(admission);
		boolean deleted = admissionService.delete(createdAdmission.getUuid());
		assertTrue(deleted);
		deleted = admissionService.delete(createdAdmission.getUuid());
		assertFalse(deleted);
		assertEquals(createdAdmission, admission);
	}

	private Admission generateAdmission() {
		Admission admission = new Admission();
		Branch branch = generateBranch();
		admission.setBranch(branch);
		admission.setPatient(generatePatient());
		admission.setWard(branch.getWards().get(1));
		admission.setAdmissionDate(faker.date().past(5, TimeUnit.DAYS));
		admission.setNotes(faker.lorem().sentence());
		return admission;
	}

	private Branch generateBranch() {
		Branch branch = new Branch();
		branch.setAddress(faker.address().fullAddress());
		branch.setBranchName(faker.lorem().word());
		branch.setEmail(faker.internet().safeEmailAddress());
		branch.setTelephone(faker.internet().safeEmailAddress());
		List<Ward> wards = new ArrayList<>();
		wards.add(generateWard());
		wards.add(generateWard());
		wards.add(generateWard());
		branchRepository.save(branch);
		for (Ward ward : wards) {
			ward.setBranch(branch);
			wardRepository.save(ward);
		}
		branch.setWards(wards);
		return branch;
	}

	private Ward generateWard() {
		Ward ward = new Ward();
		ward.setDoctorInCharge(faker.name().fullName());
		ward.setPricePerDay(400d);
		ward.setWardName(faker.lorem().word());
		return ward;
	}

	private Patient generatePatient() {
		String[] bloodGroups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		Patient patient = new Patient();
		patient.setName(faker.name().fullName());
		patient.setNic(faker.number().digits(12));
		patient.setDateOfBirth(faker.date().birthday());
		patient.setAddress(faker.address().fullAddress());
		patient.setEmail(faker.number().digits(10));
		patient.setMobile(faker.internet().safeEmailAddress());
		patient.setGender(faker.bool().bool() ? "Male" : "Female");
		patient.setBloodGroup(bloodGroups[faker.number().numberBetween(0, 7)]);
		patient.setContactPersonName(faker.name().fullName());
		patient.setContactPersonMobile(faker.number().digits(10));
		patient.setContactPersonEmail(faker.internet().safeEmailAddress());
		patientRepository.save(patient);
		return patient;
	}
}
