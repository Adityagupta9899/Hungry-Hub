package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungryhub.entites.Order_items;

@Repository
public interface OrderitemsRespository extends JpaRepository<Order_items,Integer> {
    
}
