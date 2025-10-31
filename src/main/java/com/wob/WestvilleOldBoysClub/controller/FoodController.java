package com.wob.WestvilleOldBoysClub.controller;

import com.wob.WestvilleOldBoysClub.model.FoodItem;
import com.wob.WestvilleOldBoysClub.model.User;
import com.wob.WestvilleOldBoysClub.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "https://work-integrated-app-amg6fcezacbvgyg0.southafricanorth-01.azurewebsites.net",
        "https://localhost:7223"
})
@RequestMapping("/food/api/v1")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/get-all-items")
    public ResponseEntity<?> getAllFoodItems() {
        return foodService.getAllFoodItems();
    }

    @PostMapping("/create-item")
    public  ResponseEntity<?> createFoodItem(@RequestBody FoodItem item) {
        return foodService.createFoodItem(item);
    }

    @PostMapping("/update-item")
    public  ResponseEntity<?> updateFoodItem(@RequestBody FoodItem item) {
        return foodService.updateFoodItem(item);
    }

    @PostMapping("/get-item-by-id")
    public ResponseEntity<?> getFoodItemById(@RequestBody FoodItem item) {
        return foodService.getFoodItemById(item);
    }

    @PostMapping("/delete-item-by-id")
    public ResponseEntity<?> deleteFoodItemById(@RequestBody FoodItem item) {
        return foodService.deleteFoodItem(item);
    }

}
