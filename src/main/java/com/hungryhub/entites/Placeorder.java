package com.hungryhub.entites;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Placeorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customer_id;
    private String name;
    private String Address;
    private String email;
    private String cont;
    private LocalDateTime added_on;
    private String payment_method;
    private String grand_total;
    private int itemid;

    public Placeorder() {
        super();
        ;
    }

    public Placeorder(int id, int customer_id, String name, String Address, String email, String cont,
            LocalDateTime added_on, String payment_method, String grand_total, int itemid) {
        this.id = id;
        this.customer_id = customer_id;
        this.name = name;
        this.Address = Address;
        this.email = email;
        this.cont = cont;
        this.added_on = added_on;
        this.payment_method = payment_method;
        this.grand_total = grand_total;
        this.itemid = itemid;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getAdded_on() {
        return this.added_on;
    }

    public void setAdded_on(LocalDateTime added_on) {
        this.added_on = added_on;
    }

    public String getPayment_method() {
        return this.payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getGrand_total() {
        return this.grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public int getItemid() {
        return this.itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", customer_id='" + getCustomer_id() + "'" +
                ", name='" + getName() + "'" +
                ", Address='" + getAddress() + "'" +
                ", email='" + getEmail() + "'" +
                ", cont='" + getCont() + "'" +
                ", added_on='" + getAdded_on() + "'" +
                ", payment_method='" + getPayment_method() + "'" +
                ", grand_total='" + getGrand_total() + "'" +
                ", itemid='" + getItemid() + "'" +
                "}";
    }

}
