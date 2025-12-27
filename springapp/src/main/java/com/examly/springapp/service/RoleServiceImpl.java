package com.examly.springapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Role;
import com.examly.springapp.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo repo;

    public Role save(Role role) {
        return repo.save(role);
    }

    public List<Role> findAll() {
        return repo.findAll();
    }

    public Role findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Role update(Long id, Role role) {
        Role r = repo.findById(id).orElseThrow();
        r.setRoleName(role.getRoleName());
        return repo.save(r);
    }

    public Page<Role> findPaginated(int page, int size) {
        return repo.findAll(PageRequest.of(page, size, Sort.by("id")));
    }
}
