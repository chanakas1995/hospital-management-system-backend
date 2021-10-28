package com.chanaka.icbt.abchms.repositories;

import com.chanaka.icbt.abchms.models.Ward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {

}
