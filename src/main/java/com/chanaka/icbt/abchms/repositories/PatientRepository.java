package com.chanaka.icbt.abchms.repositories;

import java.util.Optional;

import com.chanaka.icbt.abchms.models.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	Optional<Patient> findByUuid(String uuid);

	Optional<Patient> findByNic(String nic);
}
