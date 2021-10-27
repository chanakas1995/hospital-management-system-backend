package com.chanaka.icbt.abchms.custom.validators.implementations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chanaka.icbt.abchms.custom.validators.interfaces.Nic;

public class NicValidator implements ConstraintValidator<Nic, String> {

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return arg0 == null || arg0.matches("^\\d{9}[vVxX]$") || arg0.matches("^\\d{12}$");
	}

}
