package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.repository.UserRoleMappingRepo;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {

    @Autowired
    private UserRoleMappingRepo repo;

    @PostMapping
    public ResponseEntity<UserRoleMapping> createMapping(
            @RequestBody UserRoleMapping mapping) {

        return new ResponseEntity<>(repo.save(mapping), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserRoleMapping>> getAllMappings() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleMapping> getMappingById(@PathVariable Long id) {
        UserRoleMapping mapping = repo.findById(id).orElse(null);
        if (mapping == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapping);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleMapping> updateMapping(
            @PathVariable Long id,
            @RequestBody UserRoleMapping mapping) {

        UserRoleMapping existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setUser(mapping.getUser());
        existing.setRole(mapping.getRole());

        return ResponseEntity.ok(repo.save(existing));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleMapping>> getByUserId(
            @PathVariable Long userId) {

        List<UserRoleMapping> list = repo.findByUserId(userId);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRoleMapping> getByUserAndRole(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        UserRoleMapping mapping = repo.findByUserAndRole(userId, roleId);
        if (mapping == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapping);
    }
}
