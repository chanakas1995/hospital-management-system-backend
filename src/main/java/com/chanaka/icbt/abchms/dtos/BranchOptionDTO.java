package com.chanaka.icbt.abchms.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchOptionDTO {
	private Integer id;
	private String branchName;
	private List<WardOptionDTO> wards;
}
