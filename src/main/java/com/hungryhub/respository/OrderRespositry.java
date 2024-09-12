package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungryhub.entites.Orders;

@Repository
public interface OrderRespositry extends JpaRepository<Orders,Integer> {
    
}
