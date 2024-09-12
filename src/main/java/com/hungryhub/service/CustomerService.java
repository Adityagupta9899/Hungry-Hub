package com.hungryhub.service;

import java.util.List;

import com.hungryhub.entites.CartItem;
import com.hungryhub.entites.Customer;

public interface CustomerService {

	public Customer saveCustomer(Customer customer);

	public Customer checkContAndPassword(String cont, String password);

	public void removemsg();

	public boolean checkCont(String cont);

	public void deletecustomeryId(int Cust_id);

	public Customer updateprofile();

	public void save(Customer cust);

	public Customer getCustomerById(Integer customerId);

	public List<CartItem> getallorderfromshoppingcart(Integer custid);

	public void emptycart(Integer id);


}
