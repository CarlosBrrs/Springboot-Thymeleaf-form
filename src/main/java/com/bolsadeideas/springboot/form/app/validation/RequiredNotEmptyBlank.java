package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = RequiredValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface RequiredNotEmptyBlank {

    String message() default "La entrada no puede tener espacios en blanco ni ser vacia";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
