package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = RegexIdValidator.class) //La clase que validará se enalaza por aqui
@Retention(RUNTIME) //Se resuelve e implementa en tiempo de ejecución
@Target({FIELD, METHOD}) //Sobre quien se aplica, atributos y métodos
public @interface RegexId {

    //Se traen estos tres métodos de cualquier anotación en User y se reemplaza el mensaje por el que querramos mostrar
    String message() default "Identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
