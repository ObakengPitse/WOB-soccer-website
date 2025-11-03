package com.wob.WestvilleOldBoysClub.service;

import com.wob.WestvilleOldBoysClub.dto.OrderDTO;
import com.wob.WestvilleOldBoysClub.model.Order;
import com.wob.WestvilleOldBoysClub.model.OrderItem;
import com.wob.WestvilleOldBoysClub.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(OrderDTO dto) {
        Order order = new Order();
        order.setCreatedAt(dto.getCreatedAt());
        order.setEmail(dto.getEmail());
        order.setFullName(dto.getFullName());
        order.setPhone(dto.getPhone());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setTotal(dto.getTotal());
        order.setStatus(dto.getStatus());

        List<OrderItem> items = new ArrayList<>();
        for(OrderItem item: dto.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.getName());
            orderItem.setQty(item.getQty());
            orderItem.setPrice(item.getPrice());
            items.add(orderItem);
        }
        order.setItems(items);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByEmail(Order order) {
        return orderRepository.findByEmail(order.getEmail());
    }

    public Order getOrderById(Order order) {
        return orderRepository.findById(order.getId()).orElse(null);
    }
}
