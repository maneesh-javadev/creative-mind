package com.cmc.lgd.localbody.rules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BusinessRulesConstraintValidator.class)
public @interface BusinessRulesConstraint {
	String message() default "Business rules validation failed.";

	Class<?>[] groups() default {}; // NO_UCD (unused code)

	Class<? extends Payload>[] payload() default {};
}
