package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hungryhub.entites.Contactus;

public interface ContactusRepository extends JpaRepository<Contactus, Integer>{
    
}
