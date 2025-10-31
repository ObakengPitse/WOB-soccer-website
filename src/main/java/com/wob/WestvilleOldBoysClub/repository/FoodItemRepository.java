package com.wob.WestvilleOldBoysClub.repository;

import com.wob.WestvilleOldBoysClub.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
