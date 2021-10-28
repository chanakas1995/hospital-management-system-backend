package com.chanaka.icbt.abchms.repositories;

import java.util.Optional;

import com.chanaka.icbt.abchms.models.Admission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
	Optional<Admission> findByUuid(String uuid);
}
