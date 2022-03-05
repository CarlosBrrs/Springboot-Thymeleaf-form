package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Se enlaza con RegexId
public class RegexIdValidator implements ConstraintValidator<RegexId, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
            return true;
        }
        return false;
    }
}
