package com.bolsadeideas.springboot.form.app.filters;

import com.bolsadeideas.springboot.form.app.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RolePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        if(idString != null && idString.length() > 0) {
            Integer id = Integer.parseInt(idString);
            this.setValue(roleService.findRoleById(id));
        }
    }
}
