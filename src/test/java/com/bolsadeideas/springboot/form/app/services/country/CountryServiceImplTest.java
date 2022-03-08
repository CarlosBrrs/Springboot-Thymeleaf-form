package com.bolsadeideas.springboot.form.app.services.country;

import com.bolsadeideas.springboot.form.app.models.domain.Country;
import com.bolsadeideas.springboot.form.app.services.country.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CountryServiceImplTest {

    private CountryServiceImpl countryService;
    private Integer countryFail;
    private Integer countrySuccess;

    @BeforeEach
    public void init() {
        countryService = new CountryServiceImpl();
        countryFail = 0;
        countrySuccess = 1;
    }

    @Test
    public void shouldValidateAFilledListOfCountries() {
        //Give none
        //When
        List<Country> countries = countryService.getListOfCountryClass();
        //Then
        assertNotNull(countries);
    }

    @Test
    public void shouldFindCountryById() {
        //Give countrySuccess
        //When
        Country country = countryService.findCountryById(countrySuccess);
        //Then
        assertNotNull(country);
    }

    @Test
    public void shouldNotFindCountryByIdAndReturnNull() {
        //Give countryFail
        //When
        Country country = countryService.findCountryById(countryFail);
        //Then
        assertNull(country);
    }
}
