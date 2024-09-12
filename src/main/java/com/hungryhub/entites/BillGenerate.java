package com.hungryhub.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "BILL_GENERATE_TABLE")
public class BillGenerate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int orderid;
	private double bill_amount;
	private int custid;
	private String payment_status;
	private String mode;


	public BillGenerate() {
	}

	public BillGenerate(int id, int orderid, double bill_amount, int custid, String payment_status, String mode) {
		this.id = id;
		this.orderid = orderid;
		this.bill_amount = bill_amount;
		this.custid = custid;
		this.payment_status = payment_status;
		this.mode = mode;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderid() {
		return this.orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public double getBill_amount() {
		return this.bill_amount;
	}

	public void setBill_amount(double bill_amount) {
		this.bill_amount = bill_amount;
	}

	public int getCustid() {
		return this.custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getPayment_status() {
		return this.payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public BillGenerate id(int id) {
		setId(id);
		return this;
	}

	public BillGenerate orderid(int orderid) {
		setOrderid(orderid);
		return this;
	}

	public BillGenerate bill_amount(double bill_amount) {
		setBill_amount(bill_amount);
		return this;
	}

	public BillGenerate custid(int custid) {
		setCustid(custid);
		return this;
	}

	public BillGenerate payment_status(String payment_status) {
		setPayment_status(payment_status);
		return this;
	}

	public BillGenerate mode(String mode) {
		setMode(mode);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof BillGenerate)) {
			return false;
		}
		BillGenerate billGenerate = (BillGenerate) o;
		return id == billGenerate.id && orderid == billGenerate.orderid && bill_amount == billGenerate.bill_amount && custid == billGenerate.custid && Objects.equals(payment_status, billGenerate.payment_status) && Objects.equals(mode, billGenerate.mode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, orderid, bill_amount, custid, payment_status, mode);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", orderid='" + getOrderid() + "'" +
			", bill_amount='" + getBill_amount() + "'" +
			", custid='" + getCustid() + "'" +
			", payment_status='" + getPayment_status() + "'" +
			", mode='" + getMode() + "'" +
			"}";
	}

}
