package com.bolsadeideas.springboot.form.app.services;

import com.bolsadeideas.springboot.form.app.models.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> getCountries();
    Country findCountryById(Integer id);

}
