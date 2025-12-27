package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.AccountStatusLog;
import com.examly.springapp.repository.AccountStatusLogRepo;

@RestController
@RequestMapping("/api/accountStatusLogs")
public class AccountStatusLogController {

    @Autowired
    private AccountStatusLogRepo repo;

    @PostMapping
    public ResponseEntity<AccountStatusLog> addStatusLog(@RequestBody AccountStatusLog log) {
        return new ResponseEntity<>(repo.save(log), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountStatusLog>> getAllStatusLogs() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountStatusLog> getStatusLogById(@PathVariable Long id) {
        AccountStatusLog log = repo.findById(id).orElse(null);
        if (log == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(log);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountStatusLog> updateStatusLog(
            @PathVariable Long id,
            @RequestBody AccountStatusLog log) {

        AccountStatusLog existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setOldStatus(log.getOldStatus());
        existing.setNewStatus(log.getNewStatus());

        return ResponseEntity.ok(repo.save(existing));
    }
}
