package com.chanaka.icbt.abchms.custom.validators.implementations;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chanaka.icbt.abchms.custom.validators.interfaces.PastDateOrEqual;

public class PastDateOrEqualValidator implements ConstraintValidator<PastDateOrEqual, Date> {

	@Override
	public boolean isValid(Date arg0, ConstraintValidatorContext arg1) {
		return arg0 == null || arg0.before(new Date()) || arg0.equals(new Date());
	}

}
