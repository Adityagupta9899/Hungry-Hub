package com.hungryhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hungryhub.entites.CustomCustomerDetail;
import com.hungryhub.entites.Customer;
import com.hungryhub.respository.CustomerRespository;


@Service
public class customerUserService implements UserDetailsService {

	@Autowired
	private CustomerRespository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// System.out.println(username+" username object");
		Customer customer = customerRepository.findByEmail(username);
		if (customer == null) {
			// System.out.println("hiiiiiiiiiiiiiiiiiii exceptio");
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomCustomerDetail(customer);
	}
}
