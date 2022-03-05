package com.bolsadeideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class RequiredValidator implements ConstraintValidator<RequiredNotEmptyBlank, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        //if(StringUtils.hasText(s) || s == null)
        if(s.isBlank() || s.isEmpty() || s == null) {
            return false;
        }
        return true;
    }
}
