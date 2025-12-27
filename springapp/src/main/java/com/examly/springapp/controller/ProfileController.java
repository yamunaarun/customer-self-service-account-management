package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Profile;
import com.examly.springapp.repository.ProfileRepo;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileRepo repo;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(repo.save(profile), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = repo.findById(id).orElse(null);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable Long id,
            @RequestBody Profile profile) {

        Profile existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        existing.setFirstName(profile.getFirstName());
        existing.setLastName(profile.getLastName());
        existing.setAddress(profile.getAddress());
        existing.setDob(profile.getDob());
        existing.setGender(profile.getGender());
        existing.setUser(profile.getUser());

        return ResponseEntity.ok(repo.save(existing));
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Profile>> getProfilesByFirstName(
            @PathVariable String firstName) {

        List<Profile> profiles = repo.findByFirstName(firstName);
        if (profiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/search/{name}/{address}")
    public ResponseEntity<List<Profile>> getProfilesByNameAndAddress(
            @PathVariable String name,
            @PathVariable String address) {

        List<Profile> profiles = repo.findByNameAndAddress(name, address);
        if (profiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profiles);
    }
}
