package com.chanaka.icbt.abchms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.chanaka.icbt.abchms.AbchmsApplication;
import com.chanaka.icbt.abchms.custom.AlreadyInUseException;
import com.chanaka.icbt.abchms.models.Patient;
import com.chanaka.icbt.abchms.repositories.PatientRepository;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AbchmsApplication.class)
public class PatientServiceTest {

	@Autowired
	private PatientService patientService;

	@Autowired
	private PatientRepository patientRepository;

	Faker faker = new Faker();

	@BeforeEach
	void clearTables() {
		patientRepository.deleteAll();
	}

	@Test
	void testIndex() throws AlreadyInUseException {
		Patient patient1 = generatePatient();
		Patient patient2 = generatePatient();
		Patient createdPatient1 = patientService.store(patient1);
		Patient createdPatient2 = patientService.store(patient2);
		List<Patient> createdPatientsList = new ArrayList<>();
		createdPatientsList.add(createdPatient1);
		createdPatientsList.add(createdPatient2);
		List<Patient> receivedPatientsList = patientService.index();
		assertEquals(createdPatientsList, receivedPatientsList);
	}

	@Test
	void testFind() throws AlreadyInUseException {
		Patient patient = generatePatient();
		Patient createdPatient = patientService.store(patient);
		assertEquals(createdPatient, patient);
	}

	@Test
	void testStore() throws AlreadyInUseException {
		Patient patient = generatePatient();
		Patient createdPatient = patientService.store(patient);
		assertEquals(createdPatient, patient);
	}

	@Test
	void testUpdate() throws AlreadyInUseException {
		Patient patient = generatePatient();
		Patient createdPatient = patientService.store(patient);
		createdPatient.setName(faker.name().fullName());
		assertThrows(AlreadyInUseException.class, () -> {
			patientService.update(UUID.randomUUID().toString(), createdPatient);
		});
		Patient updatedPatient = patientService.update(createdPatient.getUuid(), createdPatient);
		assertEquals(createdPatient, updatedPatient);
	}

	@Test
	void testDelete() throws AlreadyInUseException {
		Patient patient = generatePatient();
		Patient createdPatient = patientService.store(patient);
		boolean deleted = patientService.delete(createdPatient.getUuid());
		assertTrue(deleted);
		deleted = patientService.delete(createdPatient.getUuid());
		assertFalse(deleted);
		assertEquals(createdPatient, patient);
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
		return patient;
	}
}
