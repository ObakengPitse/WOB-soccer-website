package com.wob.WestvilleOldBoysClub.service;

import com.wob.WestvilleOldBoysClub.model.FoodItem;
import com.wob.WestvilleOldBoysClub.repository.FoodItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodItemRepository foodItemRepository;

    public FoodService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public ResponseEntity<List<?>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemRepository.findAll());
    }

    public ResponseEntity<?> getFoodItemById(FoodItem item) {
        Optional<FoodItem> foundItem = foodItemRepository.findById(item.getId());
        if(foundItem.isPresent()){
            return ResponseEntity.ok(foundItem.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    public ResponseEntity<?> createFoodItem(FoodItem item) {
        return ResponseEntity.ok(foodItemRepository.save(item));
    }

    public ResponseEntity<?> updateFoodItem(FoodItem item) {
        Optional<FoodItem> foundItem = foodItemRepository.findById(item.getId());

        if(foundItem.isPresent()){
            if(item.getName() != null) {
                foundItem.get().setName(item.getName());
            }
            foundItem.get().setPrice(item.getPrice());
            foundItem.get().setCategory(item.getCategory());
            foundItem.get().setImageUrl(item.getImageUrl());
            foundItem.get().setDescription(item.getDescription());

            foodItemRepository.save(foundItem.get());
            return ResponseEntity.ok("Item updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }

    public ResponseEntity<?> deleteFoodItem(FoodItem item) {
        Optional<FoodItem> foundItem = foodItemRepository.findById(item.getId());
        if(foundItem.isPresent()) {
            foodItemRepository.delete(foundItem.get());
            return ResponseEntity.ok("Item deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
    }
}
