package com.example.hotelbookingassignment.repository;

import com.example.hotelbookingassignment.ds.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findRoleByRoleName(String roleName);
}
