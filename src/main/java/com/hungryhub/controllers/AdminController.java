package com.hungryhub.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Blob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hungryhub.entites.BillGenerate;
import com.hungryhub.entites.Category;
import com.hungryhub.entites.Contactus;
import com.hungryhub.entites.FoodItems;
import com.hungryhub.entites.Orders;
import com.hungryhub.entites.Payment;
import com.hungryhub.service.AdminService;
import com.hungryhub.service.CustomerServiceImpl;
import com.hungryhub.service.FoodItemservice;


@Controller
public class AdminController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private AdminService adminService;

    @Autowired
    private FoodItemservice foodItemservice;

    //It will open the Admin Page
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    //It will Open the Contact US Page
    @GetMapping("/contactus")
    public String contactus() {
        return "contactus";
    }

    //It will save the Contact US Detalis
    @PostMapping("/savecontact")
    public String savecontact(Contactus contactus){
        adminService.savedetalis(contactus);
        return "redirect:/contactus";
    }

    @GetMapping("/category/{custid}")
    public String category(@PathVariable("custid") int custid, Model model) {
        List<FoodItems> entities = foodItemservice.getAllEntities();
        model.addAttribute("entities", entities);
        model.addAttribute("custid", custid);
        return "category";
    }

    @GetMapping("/feedbacklist")
    public String feedbacklist(Model model) {
        model.addAttribute("entities", adminService.getAllcontactus());
        return "feedbacklist";
    }

    @GetMapping("/customerlist")
    public String customerlist(Model model) {
        model.addAttribute("entities", customerServiceImpl.getAllEntities());
        return "customerlist";
    }

    @GetMapping("/categoryadd")
    public String categoryadd(Model model) {
        model.addAttribute("category", new Category());
        return "categoryadd";
    }

    @PostMapping("/categoryadd")
    public String saveOrUpdateCategory(@ModelAttribute Category category) {
        category.setAddOn(LocalDateTime.now());
        category.setUpdatedOn(LocalDateTime.now());
        adminService.saveOrUpdateCategory(category);
        return "redirect:/categorylist";
    }

    @GetMapping("/foodlist")
    public String foodlist(Model model) {
        List<FoodItems> entities = foodItemservice.getAllEntities();
        model.addAttribute("entities", entities);
        return "foodlist";
    }

    @GetMapping("/foodadd")
    public String showAddFoodForm(Model model) {
        model.addAttribute("foodItem", new FoodItems());
        return "foodadd"; // Ensure this matches your Thymeleaf template name
    }

    @GetMapping("/categorylist")
    public String categorylist(Model model) {
        List<Category> entities = adminService.getAllCategories();
        model.addAttribute("entities", entities);
        return "categorylist";
    }

    @GetMapping("/paymenntlist")
    public String paymenntlist(Model model) {
        List<Payment> entities = adminService.getAllpayments();
        model.addAttribute("entities", entities);
        return "paymenntlist";
    }

    @GetMapping("/billdetalis")
    public String billdetalis(Model model) {
        List<BillGenerate> entities = adminService.getAllbill();
        model.addAttribute("entities", entities);
        return "billdetalis";
    }

    @GetMapping("/orderlist")
    public String orderlist(Model model) {
        List<Orders> entities = adminService.getAllOrders();
        model.addAttribute("entities", entities);
        return "orderlist";
    }

    @PostMapping("/saveitem")
    public String saveItem(
            @RequestParam(value = "Item_id", required = false) Integer itemId,
            @RequestParam("Item_name") String itemName,
            @RequestParam("Item_price") int itemPrice,
            @RequestParam("Item_photo") MultipartFile itemPhoto,
            @RequestParam("Category") String category,
            @RequestParam("Status") String status,
            @RequestParam("Description") String description,
            @RequestParam("Type") String type) {

        FoodItems foodItems;

        if (itemId != null) {
            foodItems = foodItemservice.getFoodItemsById(itemId).orElse(new FoodItems());
        } else {
            foodItems = new FoodItems();
        }

        foodItems.setItem_name(itemName);
        foodItems.setItem_price(itemPrice);
        foodItems.setCategory(category);
        foodItems.setDescription(description);
        foodItems.setStatus(status);
        foodItems.setType(type);

        try {
            byte[] bytes = itemPhoto.getBytes();
            Blob blob = new SerialBlob(bytes);
            foodItems.setItem_photo(blob);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        foodItemservice.saveOrUpdateFoodItems(foodItems);
        return "redirect:/foodlist";
    }

    @GetMapping("/foodlist/update/{Item_id}")
    public String updateFoodList(@PathVariable int Item_id, Model model) {
        Optional<FoodItems> foodItems = foodItemservice.getFoodItemsById(Item_id);
        if (foodItems.isPresent()) {
            model.addAttribute("foodItem", foodItems.get());
            return "foodadd";
        } else {
            return "redirect:/foodlist";
        }
    }

    @GetMapping("/foodlist/delete/{Item_id}")
    public String deleteitem(@PathVariable int Item_id) {
        foodItemservice.deleteItemById(Item_id);
        return "redirect:/foodlist";
    }

    @GetMapping("/customerlist/delete/{Cust_id}")
    public String deletecustomer(@PathVariable int Cust_id) {
        customerServiceImpl.deletecustomeryId(Cust_id);
        return "redirect:/customerlist";
    }

    @GetMapping("/categorylist/delete/{Category_id}")
    public String deletecategory(@PathVariable int Category_id) {
        adminService.deleteCategory(Category_id);
        return "redirect:/categorylist";
    }

    @GetMapping("/categorylist/update/{categoryId}")
    public String updateCategory(@PathVariable int categoryId, Model model) {
        Optional<Category> category = adminService.getCategoryById(categoryId);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoryadd";

        } else {
            return "redirect:/categorylist";
        }
    }

    @GetMapping("/orderlist/delete/{id}")
    public String deleteorder(@PathVariable int id) {
        adminService.deleteorder(id);
        return "redirect:/orderlist";
    }

    @GetMapping("/orderlist/update/{id}")
    public String updateoderstatus(@PathVariable int id, Model model) {
        Optional<Orders> order = adminService.getorderById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "updateorder";

        } else {
            return "redirect:/orderlist";
        }
    }

    @GetMapping("/billdetalis/delete/{id}")
    public String deletebill(@PathVariable int id) {
        adminService.deletebill(id);
        return "redirect:/billdetalis";
    }

    @GetMapping("/feedbacklist/delete/{id}")
    public String feedbacklist(@PathVariable int id) {
        adminService.deleteContactus(id);
        return "redirect:/feedbacklist";
    }

    @GetMapping("/paymenntlist/delete/{id}")
    public String paymentdelete(@PathVariable int id) {
        adminService.deletepayemt(id);
        return "redirect:/paymenntlist";
    }
}
