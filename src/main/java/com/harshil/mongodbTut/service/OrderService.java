package com.harshil.mongodbTut.service;

import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order insertNewOrder(Order order) {

        order = orderRepository.insert(order);
        System.out.println(order);

        return order;

    }

    public List<Order> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        if(orders.isEmpty()) {

            throw new RuntimeException("No Orders Found.");

        }

        return orders;

    }
}
