package com.wob.WestvilleOldBoysClub.repository;

import com.wob.WestvilleOldBoysClub.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmail(String email);
}
