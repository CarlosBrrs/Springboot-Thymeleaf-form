package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.User;
import com.bolsadeideas.springboot.form.app.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
@SessionAttributes("user") //Al pasar del form al filled, el campo id se pierde, esta anotacion me permite persistir ese campo durante toda la sesion
public class FormController {

    //En este caso se usa la clase que implementa porque solo lo implementa el
    @Autowired
    private UserValidator validator;

    //Mostrar el fomrulario en pantalla
    @GetMapping("/form")
    public String viewForm(Model model) {

        model.addAttribute("title", "New form");

        //Este usuario sirve para que no marque error al tratar de insertar los atributos en el formulario de un usuario que aun no ha diligenciado nada
        User newUser = new User();
        newUser.setName("John");
        newUser.setLastname("Doe");
        newUser.setId("123.456.789-K");
        model.addAttribute("user", newUser);
        return "form";
    }

/*
    //Procesar los datos enviados por el usuario
    //@RequestParam para capturar los valores del formulario con name en sus etiquetas
    @PostMapping("/form")
    public String processForm(
            Model model, @RequestParam String username, @RequestParam String password, @RequestParam String email) {

        //Al ser una clase con datos (que se deben persistir, o eliminar, o mostrar, etc), y no con un servicio
        //que "prestar", esta no se inyecta
        User newUser = new User(username, password, email);

        model.addAttribute("title", "Filled form");
        model.addAttribute("newUser", newUser);
        return "filled";
    }

 */

    /*
    Se puede usar el metodo anterior o este donde se pasa en la firma el objeto User, que tiene los mismos
    atributos que los input.name del formulario, y si tiene setters, los usará para poblarse automaticamente

    El User DEBE ser el primero en la firma del metodo

    Para habilitar la validacion desde Spring se usa @Valid al parámetro y se especifican las validaciones en
    la propia clase usando anotaciones predefinidas o personalizadas
     */
    @PostMapping("/form")
    public String processForm(/*@ModelAtributte("myUser")*/@Valid User newUser, BindingResult result, Model model, SessionStatus session) {
                              /*sirve para darle ese nombre al objeto que va a la vista de forma automatica en caso que falle la validacion*/

        model.addAttribute("title", "Filled form");
        validator.validate(newUser, result);

        /*
        Para interactuar con un objeto ya validado, se usa BindingResult en la firma del metodo y representa
        el resultado de la validación (contiene los mensajes de error en caso que ocurran)

        El BindingResult SIEMPRE debe estar JUSTO DESPUÉS del objeto que se valida
         */
        if (result.hasErrors()) {

            /*Se puede omitir este codigo para manejar errores y usar el #fields y th:errors propio de Thymeleaf

            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(
                    err.getField(),
                    "El campo ".concat(err.getField()).concat(" presenta estos errores: ").concat(err.getDefaultMessage())));
            //Si result tiene errores, los guardaré en un map, result.getFieldErrors() me retorna una lista de FieldError err, los itero: en errors haces put de (campo que falló, mensaje) y retorno al formulario vacio
            model.addAttribute("error", errors);*/
            model.addAttribute("title", "Please refill the form correctly");

            //Cuando se valida el formulario y se retorna al form nuevamente, lleva un objeto user (nombre de la clase en minusculas) automaticamente, con todos sus atributos, pudiendo llamarlo con thymeleaf
            return "form";
        }
        model.addAttribute("newUser", newUser);
        //Cuando finaliza el proceso se debe limpiar con setComplete y de forma automatica se elimina el objeto usuario de la sesion
        session.setComplete();
        return "filled";
    }

}
