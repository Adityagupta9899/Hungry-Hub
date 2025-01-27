package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungryhub.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
