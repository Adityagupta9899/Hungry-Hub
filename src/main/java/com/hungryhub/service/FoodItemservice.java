package com.hungryhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungryhub.entites.FoodItems;
import com.hungryhub.respository.FooditemsRespository;

@Service
public class FoodItemservice {

  @Autowired
  private FooditemsRespository fooditemsRespository;

  public FoodItems saveitem(FoodItems foodItems) {
    return fooditemsRespository.save(foodItems);
  }

  public List<FoodItems> getAllEntities() {
    return fooditemsRespository.findAll();
  }

  public void deleteItemById(int Item_id) {
    fooditemsRespository.deleteById(Item_id);
  }

  public Optional<FoodItems> getFoodItemsById(int Item_id) {
    return fooditemsRespository.findById(Item_id);
  }

  public FoodItems saveOrUpdateFoodItems(FoodItems foodItems) {
    return fooditemsRespository.save(foodItems);
  }

  public List<FoodItems> getAllFoodItems() {
    return fooditemsRespository.findAll();
  }

  public FoodItems getFoodItemById(int Item_id) {
    return fooditemsRespository.findById(Item_id).orElse(null);
  }

}
