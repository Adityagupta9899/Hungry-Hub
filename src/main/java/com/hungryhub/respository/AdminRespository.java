package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hungryhub.entites.Admin;

public interface AdminRespository extends JpaRepository<Admin, String> {

    Admin findByAdminIdAndPassword(String adminId, String password);

}
