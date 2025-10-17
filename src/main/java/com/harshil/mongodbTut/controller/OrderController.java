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

    @GetMapping("/get/order/orderId/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {

        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.FOUND);

    }

    @GetMapping("/get/orders/by")
    public ResponseEntity<List<Order>> getOrderByStatusAndTotalPriceGreaterThan(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(name = "sortDirection", defaultValue = "asc", required = false) String sortDirection,
            @RequestParam(name = "sortBy", defaultValue = "totalPrice", required = false) String sortBy,
            @RequestParam(name = "status", defaultValue = "Pending", required = false) String status,
            @RequestParam(name = "totalPrice", defaultValue = "1000.00", required = false) Double totalPrice
    ) {

        return new ResponseEntity<>(orderService.getOrderByStatusAndTotalPriceGreaterThan(pageNumber, pageSize, sortDirection, sortBy, status, totalPrice), HttpStatus.FOUND);

    }

    @PutMapping("/update/order/orderId/{orderId}")
    public ResponseEntity<Order> updateOrderById(@PathVariable String orderId,
                                                 @RequestBody Order order) {

        return new ResponseEntity<>(orderService.updateOrderById(orderId, order), HttpStatus.OK);

    }

    @DeleteMapping("/delete/order/orderId/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId) {

        return new ResponseEntity<>(orderService.deleteOrderById(orderId), HttpStatus.OK);

    }

}
