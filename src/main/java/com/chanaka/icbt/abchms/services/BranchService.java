package com.chanaka.icbt.abchms.services;

import java.util.List;

import com.chanaka.icbt.abchms.models.Branch;
import com.chanaka.icbt.abchms.repositories.BranchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
	
	@Autowired
	BranchRepository branchRepository;

	public List<Branch> index() {
		return branchRepository.findAll();
	}

}
