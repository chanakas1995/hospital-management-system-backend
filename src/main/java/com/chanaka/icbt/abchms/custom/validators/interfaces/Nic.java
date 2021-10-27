package com.chanaka.icbt.abchms.custom.validators.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.chanaka.icbt.abchms.custom.Texts;
import com.chanaka.icbt.abchms.custom.validators.implementations.NicValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NicValidator.class)
@Documented
public @interface Nic {

	String message() default Texts.INVALID;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
