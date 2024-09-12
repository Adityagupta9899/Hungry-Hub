package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hungryhub.entites.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
}
