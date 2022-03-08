package com.bolsadeideas.springboot.form.app.models.domain;

import com.bolsadeideas.springboot.form.app.validation.RegexId;
import com.bolsadeideas.springboot.form.app.validation.RequiredNotEmptyBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class User {

    //Se comentan las validaciones para validar desde la clase UserValidator
    //@Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
    @RegexId //Se puede cambiar el msj de la anotación desde aqui, desde la anotación o desde message.properties
    private String id;

    //Los atributos deben ser iguales a los name en el form que va a recibir la información
    //Las validaciones que se activaron en el controller con @Valid se especifican acá
    //@NotEmpty(message = "El username no puede ser vacio") //Distinto de null y no vacio
    @NotBlank //Valida que no sea nulo, ni vacio, ni de espacios en blanco
    @Size(min = 4, max = 12, message = "Longitud incorrecta (4-12)") //Solo para String, para numeros se usa @Min y @Max
    private String username;

    //@NotEmpty(message = "El nombre no puede ser vacio") //Envia msj personalizado al error
    private String name;

    //@NotEmpty(message = "El apellido no puede ser vacio") //Los mensajes desde el messages.properties van a sobreescribir estos mensajes si aplica
    @RequiredNotEmptyBlank
    private String lastname;

    @NotEmpty(message = "La contraseña no puede ser vacia")
    private String password;

    @NotEmpty(message = "El email no puede ser vacio")
    @Email(message = "Correo con formato incorrecto") //Valida que tenga @
    private String email;

    @NotNull //Para objetos
    @Min(5)
    @Max(5000)
    private Integer count;

    //Validar fechas en pasado o futuro
    //@Future
    @Past
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Mantiene siempre el mismo formato. El formato que coincide con la validación del front es yyyy-MM-dd
    private Date birthDate;

/*  Se comenta para implementar el country desde una clase
    @NotBlank
    private String country;*/

    //@Valid //Activa las validaciones dentro de Country
    @NotNull //Cuando se valida el objeto completo se hace con NotNull, y no solo con Valid para un atributo concreto
    private Country country;

    /*Se comenta para implementar los roles desde una clase
    @NotEmpty
    private List<String> roles;
 */

    @NotEmpty
    private List<Role> roles;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /*public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }*/

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
/*
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
 */

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
