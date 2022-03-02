package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class User {

    //Los atributos deben ser iguales a los name en el form que va a recibir la información
    //Las validaciones que se activaron en el controller con @Valid se especifican acá
    @NotEmpty //Distinto de null y no vacio
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
