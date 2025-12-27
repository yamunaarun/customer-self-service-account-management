package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.UserRoleMapping;

@Repository
public interface UserRoleMappingRepo extends JpaRepository<UserRoleMapping, Long> {

    @Query("SELECT u FROM UserRoleMapping u WHERE u.user.id = :userId")
    List<UserRoleMapping> findByUserId(Long userId);

    @Query("SELECT u FROM UserRoleMapping u WHERE u.user.id = :userId AND u.role.id = :roleId")
    UserRoleMapping findByUserAndRole(Long userId, Long roleId);
}
