package com.bolsadeideas.springboot.form.app.validation;

import com.bolsadeideas.springboot.form.app.models.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
Se pueden validar los atributos desde una clase que implemente Validator de springframework
Anotar con component para que se pueda inyectar en el contenedor y validar*/
@Component
public class UserValidator implements Validator {

    //Metodo para validar que el objeto que estamos validando corresponde al tipo User, en este caso
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        //User user = (User) target;

        //Se puede usar ValidationUtils o bloque IF
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.name");

        /* Se comenta porque la validación del id se realiza desde la anotación creada
        if(!user.getId().matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")) {
            errors.rejectValue("id", "Pattern.user.id");
            //cada mensaje de error debe estar registrado en el messages.properties
        }*/

    }
}
