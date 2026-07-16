package com.jwt_auth.repository;

import com.jwt_auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByName (String name);
    Optional<Permission> findByName(String name);
}
