package com.hungryhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hungryhub.entites.FoodItems;
import com.hungryhub.service.FoodItemservice;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FoodController {

    @Autowired
    private FoodItemservice foodItemService;

    private static final Logger logger = LoggerFactory.getLogger(FoodController.class);

    @GetMapping("/foodimage/{id}")
    public ResponseEntity<byte[]> getFoodImage(@PathVariable int id) {
        Optional<FoodItems> foodItems = foodItemService.getFoodItemsById(id);
        if (foodItems.isPresent()) {
            Blob blob = foodItems.get().getItem_photo();
            byte[] imageBytes = null;
            try {
                imageBytes = blob.getBytes(1, (int) blob.length());
                logger.info("Image bytes retrieved successfully for Item_id: " + id);
            } catch (SQLException e) {
                logger.error("Error while reading Blob for Item_id: " + id, e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            logger.info("Returning image with content type IMAGE_JPEG for Item_id: " + id);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            logger.warn("Food item not found for Item_id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
