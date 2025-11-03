package com.wob.WestvilleOldBoysClub.controller;

import com.wob.WestvilleOldBoysClub.dto.OrderDTO;
import com.wob.WestvilleOldBoysClub.model.Order;
import com.wob.WestvilleOldBoysClub.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "https://work-integrated-app-amg6fcezacbvgyg0.southafricanorth-01.azurewebsites.net",
        "https://localhost:7223"
})
@RequestMapping("/orders/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save-order")
    public Order createOrder(@RequestBody OrderDTO order) {
        return orderService.saveOrder(order);
    }

    @PostMapping("/get-all-orders")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/get-all-by-email")
    public List<Order> getOrdersByEmail(@RequestBody Order order) {
        return orderService.getOrdersByEmail(order);
    }

    @PostMapping("/get-order-by-id")
    public Order getOrder(@RequestBody Order order) {
        return orderService.getOrderById(order);
    }
}
