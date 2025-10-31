package com.wob.WestvilleOldBoysClub.repository;

import com.wob.WestvilleOldBoysClub.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
