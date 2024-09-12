package com.hungryhub.entites;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FEEDBACK_TABLE")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Feed_id;
	@OneToOne
	@JoinColumn(name = "Cust_id")
	private Customer customer;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String Feed_msg;
	
	
	public Feedback(int feed_id, Customer customer, Date date, String feed_msg) {
		super();
		Feed_id = feed_id;
		this.customer = customer;
		this.date = date;
		Feed_msg = feed_msg;
	}


	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getFeed_id() {
		return Feed_id;
	}


	public void setFeed_id(int feed_id) {
		Feed_id = feed_id;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getFeed_msg() {
		return Feed_msg;
	}


	public void setFeed_msg(String feed_msg) {
		Feed_msg = feed_msg;
	}


	
	
	
}
