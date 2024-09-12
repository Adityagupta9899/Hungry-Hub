package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hungryhub.entites.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer> {

	public Customer findByCont(String cont);

	Customer findByEmail(String email);

	public Customer findByContAndPassword(String cont, String password);

	public boolean existsByCont(String cont);

}
