package com.hungryhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hungryhub.entites.CartItem;
import com.hungryhub.entites.Customer;
import com.hungryhub.respository.CartItemRepository;
import com.hungryhub.respository.CustomerRespository;

import javax.servlet.http.HttpSession;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CustomerRespository customerRespository;

	@Override
	public Customer saveCustomer(Customer customer) {
		Customer newcust = customerRespository.save(customer);
		return newcust;
	}

	@Override
	public void removemsg() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}

	@Override
	public boolean checkCont(String cont) {
		return customerRespository.existsByCont(cont);
	}

	@Override
	public Customer checkContAndPassword(String cont, String password) {
		return customerRespository.findByContAndPassword(cont, password);
	}

	public List<Customer> getAllEntities() {
		return customerRespository.findAll();
	}

	@Override
	public void deletecustomeryId(int Cust_id) {
		customerRespository.deleteById(Cust_id);
	}

	public Customer updateprofile() {
		// Replace with actual user retrieval logic
		return customerRespository.findById(1).orElse(null);
	}

	@Override
	public void save(Customer cust) {
		customerRespository.save(cust);
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		Optional<Customer> optional = customerRespository.findById(customerId);
		Customer cust = null;
		if (optional.isPresent()) {
			cust = optional.get();
		} else {
			throw new RuntimeException("Employee Not Found for id:" + customerId);
		}
		return cust;

	}

	@Override
	public List<CartItem> getallorderfromshoppingcart(Integer custid) {
		List<CartItem> cartItems = this.cartItemRepository.findcartitemwithcustomeid(custid);
		return cartItems;
	}

	@Override
	public void emptycart(Integer customerId) {
		this.cartItemRepository.emptycart(customerId);
	}

	// @Override
	// public List<Placeorder> getalldetailfromshoppingcart(Integer custid) {
	// List<Placeorder> orderplace = this.placeorderRespsoitory.findAll();
	// return orderplace;
	// }

}
