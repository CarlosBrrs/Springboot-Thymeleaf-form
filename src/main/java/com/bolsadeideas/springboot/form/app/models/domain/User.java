package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    @Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
    private String id;

    //Los atributos deben ser iguales a los name en el form que va a recibir la información
    //Las validaciones que se activaron en el controller con @Valid se especifican acá
    @NotEmpty(message = "El username no puede ser vacio") //Distinto de null y no vacio
    @Size(min = 4, max = 12, message = "Longitud incorrecta (4-12)") //Solo para String, para numeros se usa @Min y @Max
    private String username;

    @NotEmpty(message = "El nombre no puede ser vacio") //Envia msj personalizado al error
    private String name;

    @NotEmpty(message = "El apellido no puede ser vacio") //Los mensajes desde el messages.properties van a sobreescribir estos mensajes si aplica
    private String lastname;

    @NotEmpty(message = "La contraseña no puede ser vacia")
    private String password;

    @NotEmpty(message = "El email no puede ser vacio")
    @Email(message = "Correo con formato incorrecto") //Valida que tenga @
    private String email;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
