package com.chanaka.icbt.abchms.repositories;

import com.chanaka.icbt.abchms.models.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
