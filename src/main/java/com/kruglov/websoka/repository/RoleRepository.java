package com.kruglov.websoka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kruglov.websoka.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String role);
    
}
