package com.hungryhub.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hungryhub.entites.Category;

public interface CategoryRespository extends JpaRepository<Category, Integer> {

}
