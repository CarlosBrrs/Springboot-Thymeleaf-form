package com.bolsadeideas.springboot.form.app.filters;

import java.beans.PropertyEditorSupport;

//Debe extender de PropertyEditorSupport
public class UpperCaseFilter extends PropertyEditorSupport {

    //Se sobreescribe el método que actuará
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase());
    }
}
