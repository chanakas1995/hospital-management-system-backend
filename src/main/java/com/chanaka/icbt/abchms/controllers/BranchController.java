package com.chanaka.icbt.abchms.controllers;

import java.util.stream.Stream;

import com.chanaka.icbt.abchms.custom.DataConverter;
import com.chanaka.icbt.abchms.custom.ResponseBuilder;
import com.chanaka.icbt.abchms.custom.ResponseType;
import com.chanaka.icbt.abchms.dtos.BranchOptionDTO;
import com.chanaka.icbt.abchms.services.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@GetMapping(path = "/options")
	public ResponseEntity<?> getOptions() {
		Stream<Object> data = branchService.index().stream()
				.map(patient -> DataConverter.convert(patient, BranchOptionDTO.class));
		return ResponseBuilder.build("Patients", ResponseType.FOUND, data);
	}

}
