package com.chanaka.icbt.abchms.custom.validators.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.chanaka.icbt.abchms.custom.Texts;
import com.chanaka.icbt.abchms.custom.validators.implementations.PastDateOrEqualValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastDateOrEqualValidator.class)
@Documented
public @interface PastDateOrEqual {

	String message() default Texts.PAST_DATE_OR_EQUAL;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
