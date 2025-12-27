package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Profile;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {

    List<Profile> findByFirstName(String firstName);

    @Query("SELECT p FROM Profile p WHERE p.firstName = :name AND p.address = :address")
    List<Profile> findByNameAndAddress(String name, String address);
}
