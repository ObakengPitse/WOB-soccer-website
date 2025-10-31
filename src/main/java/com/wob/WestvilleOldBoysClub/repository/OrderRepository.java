package com.wob.WestvilleOldBoysClub.repository;

import com.wob.WestvilleOldBoysClub.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
