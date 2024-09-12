package com.hungryhub.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "CUSTOMER_TABLE")

public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Cust_name;
	private String Address;
	private String email;
	@Column(unique = true)
	private String cont;
	private String password;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "Customer_id", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles;

	public Customer() {
		super();
	}

	public Customer(int id, String Cust_name, String Address, String email, String cont, String password,
			List<Role> roles) {
		this.id = id;
		this.Cust_name = Cust_name;
		this.Address = Address;
		this.email = email;
		this.cont = cont;
		this.password = password;
		this.roles = roles;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int ID) {
		this.id = ID;
	}

	public String getCust_name() {
		return this.Cust_name;
	}

	public void setCust_name(String Cust_name) {
		this.Cust_name = Cust_name;
	}

	public String getAddress() {
		return this.Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCont() {
		return this.cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getID() + "'" +
				", Cust_name='" + getCust_name() + "'" +
				", Address='" + getAddress() + "'" +
				", email='" + getEmail() + "'" +
				", cont='" + getCont() + "'" +
				", password='" + getPassword() + "'" +
				", roles='" + getRoles() + "'" +
				"}";
	}

}
