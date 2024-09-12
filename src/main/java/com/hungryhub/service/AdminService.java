package com.hungryhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungryhub.entites.Admin;
import com.hungryhub.entites.BillGenerate;
import com.hungryhub.entites.Category;
import com.hungryhub.entites.Contactus;
import com.hungryhub.entites.Orders;
import com.hungryhub.entites.Payment;
import com.hungryhub.respository.AdminRespository;
import com.hungryhub.respository.BillGenerteRepositry;
import com.hungryhub.respository.CategoryRespository;
import com.hungryhub.respository.ContactusRepository;
import com.hungryhub.respository.OrderRespositry;
import com.hungryhub.respository.PaymentRepository;

@Service
public class AdminService {

    @Autowired
    private CategoryRespository categoryRepository;

    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private OrderRespositry orderRespositry;

    @Autowired
    private BillGenerteRepositry billGenerteRepositry;

    @Autowired
    private ContactusRepository contactusRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Orders> getAllOrders() {
        return orderRespositry.findAll();
    }

    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category saveOrUpdateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Admin checkIdAndPassword(String adminId, String password) {
        return adminRespository.findByAdminIdAndPassword(adminId, password);
    }

    public void deleteorder(int id) {
        orderRespositry.deleteById(id);
    }

    public Optional<Orders> getorderById(int id) {
        return orderRespositry.findById(id);
    }

    public List<BillGenerate> getAllbill() {
        return billGenerteRepositry.findAll();
    }

    public void deletebill(int id) {
        billGenerteRepositry.deleteById(id);
    }

    public Contactus savedetalis(Contactus contactus) {
        Contactus newdtl = contactusRepository.save(contactus);
        return newdtl;
    }

    public List<Contactus> getAllcontactus() {
        return contactusRepository.findAll();
    }

    public List<Payment> getAllpayments() {
        return paymentRepository.findAll();
    }

    public void deletepayemt(int id) {
        paymentRepository.deleteById(id);
    }

    public void deleteContactus(int id) {
        contactusRepository.deleteById(id);
    }

}