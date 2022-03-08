package com.bolsadeideas.springboot.form.app.services.country;

import com.bolsadeideas.springboot.form.app.models.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{

    private List<Country> countries;

    public CountryServiceImpl(){
        this.countries = Arrays.asList(
                new Country(1,"CO","Colombia"),
                new Country(2,"ES","España"),
                new Country(3,"MX","México"),
                new Country(4,"CL","Chile"),
                new Country(5,"CA","Canadá"),
                new Country(6,"AR","Argentina"),
                new Country(7,"PE","Perú"),
                new Country(8,"VE","Venezuela"),
                new Country(9,"UR","Uruguay")
        );
    }

    @Override
    public List<Country> getListOfCountryClass() {
        return this.countries;
    }

    @Override
    public Country findCountryById(Integer id) {

        Optional<Country> first = countries.stream().filter(country -> id.equals(country.getId())).findFirst();
        return first.isPresent() ? first.get() : null;
    }
}
