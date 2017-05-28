package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Role;

import java.util.List;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class RoleStringConverter extends StringConverter<Role> {
    @Override
    public String toString(Role role) {
        return role == null ? null : role.getRole();
    }

    @Override
    public Role fromString(String str) {
        Role role = null;
        if (str == null) {
            return role;
        } else {
            role = new Role(null, str);
        }
        return role;
    }

    public Role checkRoleInDB(Role role) {
        List<Role> roleList = roleDAO().getAll();
        for (Role r : roleList) {
            if (r.getRole().equals(role.getRole())) {
                return r;
            }
        }
        return null;
    }
}
