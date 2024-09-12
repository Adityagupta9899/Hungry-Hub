package com.hungryhub.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hungryhub.entites.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

        @Query(value = "SELECT sc.* FROM CART_ITEMS sc WHERE sc.customer_id=:customerId", nativeQuery = true)
        List<CartItem> findcartitemwithcustomeid(@Param("customerId") Integer custid);

        @Query(value = "SELECT * FROM CART_ITEMS  WHERE customer_id=:customerId and product_id=:productid", nativeQuery = true)
        CartItem finditemfromidandcustomerid(@Param("customerId") Integer customerId,
                        @Param("productid") Integer productid);

        @Modifying
        @Transactional
        @Query(value = "delete  FROM CART_ITEMS  WHERE customer_id=:customerId", nativeQuery = true)
        public void emptycart(@Param("customerId") Integer customerId);
}
