package com.chanaka.icbt.abchms.custom.validators.implementations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chanaka.icbt.abchms.custom.validators.interfaces.PhoneNumber;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return arg0 == null || arg0.matches("^0\\d{9}$");
	}

}
