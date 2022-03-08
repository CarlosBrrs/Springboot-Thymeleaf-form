package com.bolsadeideas.springboot.form.app.services.country;

import com.bolsadeideas.springboot.form.app.models.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> getListOfCountryClass();
    Country findCountryById(Integer id);

}
