package com.examly.springapp.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.examly.springapp.model.Role;

public interface RoleService {

    Role save(Role role);

    List<Role> findAll();

    Role findById(Long id);

    Role update(Long id, Role role);

    Page<Role> findPaginated(int page, int size);
}
