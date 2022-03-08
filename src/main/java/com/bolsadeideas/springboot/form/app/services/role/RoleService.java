package com.bolsadeideas.springboot.form.app.services.role;

import com.bolsadeideas.springboot.form.app.models.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> getListOfRoleClass();
    Role findRoleById(Integer id);
}
