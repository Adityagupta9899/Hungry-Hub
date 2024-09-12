package com.hungryhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungryhub.entites.CartItem;
import com.hungryhub.respository.CartItemRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

}
