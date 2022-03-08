package com.bolsadeideas.springboot.form.app.services.role;

import com.bolsadeideas.springboot.form.app.models.domain.Country;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private List<Role> roles;

    public RoleServiceImpl(){
        this.roles = Arrays.asList(
                new Role(1,"ROLE_ADMIN", "Admin"),
                new Role(2,"ROLE_USER", "User"),
                new Role(3,"ROLE_GUEST", "Guest"),
                new Role(4,"ROLE_MODERATOR", "Moderator")
        );
    }

    @Override
    public List<Role> getListOfRoleClass() {
        return this.roles;
    }

    @Override
    public Role findRoleById(Integer id) {

        Optional<Role> first = roles.stream().filter(role -> id.equals(role.getId())).findFirst();
        return first.isPresent() ? first.get() : null;
    }
}
