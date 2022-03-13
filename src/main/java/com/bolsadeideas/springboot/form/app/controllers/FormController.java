package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.filters.CountryPropertyEditor;
import com.bolsadeideas.springboot.form.app.filters.RolePropertyEditor;
import com.bolsadeideas.springboot.form.app.filters.UpperCaseFilter;
import com.bolsadeideas.springboot.form.app.models.domain.Country;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.User;
import com.bolsadeideas.springboot.form.app.services.country.CountryService;
import com.bolsadeideas.springboot.form.app.services.role.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
@SessionAttributes("user") //Al pasar del form al filled, el campo id se pierde, esta anotacion me permite persistir ese campo durante toda la sesion
public class FormController {

    //En este caso se usa la clase que implementa porque solo lo implementa el
    @Autowired
    private UserValidator validator;

    @Autowired
    private CountryService countryService;

    //Se inyecta para poder llenar el Country completo con sus atributos
    @Autowired
    private CountryPropertyEditor countryPropertyEditor;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePropertyEditor rolePropertyEditor;

    //Este metodo agregará las validaciones de UserValidator a la anotacion @Valid, de esta manera no se necesita la linea: validator.validate(newUser, result);
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);

        //Si no se llena el segundo parámetro, el filtro se aplicará a todos los atributos de la clase del primer parámetro
        binder.registerCustomEditor(String.class, "lastname", new UpperCaseFilter());

        //Va a llenar el Country con sus campos y no solo el id
        binder.registerCustomEditor(Country.class, "country", countryPropertyEditor);

        binder.registerCustomEditor(Role.class, "roles", rolePropertyEditor);
    }

    //Mostrar el fomrulario en pantalla
    @GetMapping("/form")
    public String viewForm(Model model) {

        model.addAttribute("title", "New form");

        //Este usuario sirve para que no marque error al tratar de insertar los atributos en el formulario de un usuario que aun no ha diligenciado nada
        User newUser = new User();
        newUser.setName("John");
        newUser.setLastname("Doe");
        newUser.setId("123.456.789-K");

        //debe estar por defecto habilitado o deshabilitado
        newUser.setAllowMsg(true);

        newUser.setSecretString("I am Batman");
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
    public String processForm(/*@ModelAtributte("myUser")*/@Valid User newUser, BindingResult result, Model model/*, SessionStatus session va desde el GET*/) {
                              /*sirve para darle ese nombre al objeto que va a la vista de forma automatica en caso que falle la validacion*/

        model.addAttribute("title", "Filled form"); /*ya no se necesita porque va desde el GET

        //Si se comenta este validator, se debe agregar al InitBinder para que valide desde el @Valid con lo que se configuró en la clase UserValidator
        //validator.validate(newUser, result);

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
        model.addAttribute("user", newUser);
        //Cuando finaliza el proceso se debe limpiar con setComplete y de forma automatica se elimina el objeto usuario de la sesion
        //session.setComplete(); Va desde el GET
        return "redirect:/app/getUserData";
    }

    /* Se crea este método porque al enviar por primera vez el formulario, se carga la vista filled, pero si se da refresh
    se enviará nuevamente el formulario lleno y esto puede generar duplicidad en la base de datos, lo recomendable es crear
    un método GET y ser este el responsable de entregar la visualización de los datos, llamandolo con redirect en el método POST
*/
    @GetMapping("/getUserData")
    public String getUserData (@SessionAttribute(name = "user", required = false) User newUser, Model model, SessionStatus session) {

        //El user se limina por el setComplete, asi que al refrescar el getUserData, lanzará error por pasar un usuario null, asi que lo redigirimos al formulario
        if(newUser == null) {
            return "redirect:/app/form";
        }
        model.addAttribute("title", "Filled form");
        session.setComplete();
        return "filled";
    }

    //Un método anotado así guardará lo que retorna y se pasa y se guarda en la vista como atributo y se pueden usar
    //Si no se define nombre del modelatributte, se pasa a la vista el nombre del método
    @ModelAttribute("countriesList")
    public List<String> getCountriesList() {
        return Arrays.asList(
                "Colombia",
                "España",
                "México",
                "Chile",
                "Canadá",
                "Argentina",
                "Perú",
                "Venezuela",
                "Uruguay"
        );
    }

    @ModelAttribute("countriesMap")
    public Map<String, String> getCountriesMap() {

        Map<String, String> countriesMap = new HashMap<>();
        countriesMap.put("CO","Colombia");
        countriesMap.put("ES","España");
        countriesMap.put("MX","México");
        countriesMap.put("CL","Chile");
        countriesMap.put("CA","Canadá");
        countriesMap.put("PE","Perú");
        countriesMap.put("VE","Venezuela");
        countriesMap.put("AR","Argentina");
        countriesMap.put("UR","Uruguay");

        return countriesMap;
    }

    @ModelAttribute("ListOfClassCountry")
    public List<Country> getListOfClassCountry() {

        return countryService.getListOfCountryClass();
    }

    @ModelAttribute("rolesList")
    public List<String> getRolesList() {
        return Arrays.asList(
                "ROLE_ADMIN",
                "ROLE_USER",
                "ROLE_GUEST",
                "ROLE_MODERATOR"
        );
    }

    @ModelAttribute("rolesMap")
    public Map<String, String> getRolesMap() {

        Map<String, String> rolesMap = new HashMap<>();
        rolesMap.put("ROLE_ADMIN","Admin");
        rolesMap.put("ROLE_USER","User");
        rolesMap.put("ROLE_GUEST","Guest");
        rolesMap.put("ROLE_MODERATOR","Moderator");

        return rolesMap;
    }

    @ModelAttribute("ListOfClassRole")
    public List<Role> getListOfClassRole() {

        return roleService.getListOfRoleClass();
    }

    @ModelAttribute("genders")
    public List<String> getGender() {
        return Arrays.asList(
                "Male",
                "Female"
        );
    }
}
