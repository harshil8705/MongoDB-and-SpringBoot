package com.harshil.mongodbTut.controller;

import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/insert/order")
    public ResponseEntity<Order> insertNewOrder(@RequestBody Order order) {

        return new ResponseEntity<>(orderService.insertNewOrder(order), HttpStatus.OK);

    }

    @GetMapping("/get/orders")
    public ResponseEntity<List<Order>> getAllOrders() {

        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.FOUND);

    }

}
