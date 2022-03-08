package com.bolsadeideas.springboot.form.app.services.role;

import com.bolsadeideas.springboot.form.app.models.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleServiceImplTest {

    private RoleServiceImpl roleService;
    Integer roleSuccess;
    Integer roleFail;

    @BeforeEach
    public void init() {
        roleService = new RoleServiceImpl();
        roleFail = 0;
        roleSuccess = 1;
    }

    @Test
    public void shouldValidateAFilledListOfRoles() {
        //Give none
        //When
        List<Role> roles = roleService.getListOfRoleClass();
        //Then
        assertNotNull(roles);
    }

    @Test
    public void shouldFindRoleById() {
        //Give countrySuccess
        //When
        Role role = roleService.findRoleById(roleSuccess);
        //Then
        assertNotNull(role);
    }

    @Test
    public void shouldNotFindRoleByIdAndReturnNull() {
        //Give countryFail
        //When
        Role role = roleService.findRoleById(roleFail);
        //Then
        assertNull(role);
    }
}
