package com.bolsadeideas.springboot.form.app.filters;

import com.bolsadeideas.springboot.form.app.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

//Esta clase servirá para mapear el objeto Country de User, y no solo el ID
@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CountryService countryService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        if(idString != null && !idString.isEmpty()) {
            Integer idInteger = Integer.parseInt(idString);
            this.setValue(countryService.findCountryById(idInteger));
        }
    }
}
