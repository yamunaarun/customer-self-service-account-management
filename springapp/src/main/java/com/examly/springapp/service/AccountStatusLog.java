package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Role;

public interface AccountStatusLog {

    Role addRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role updateRole(Long id, Role role);
}
