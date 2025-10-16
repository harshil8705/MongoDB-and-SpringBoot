package com.harshil.mongodbTut.service;

import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Order> getOrderByStatusAndTotalPriceGreaterThan(Integer pageNumber, Integer pageSize, String sortDirection, String sortBy, String status, Double totalPrice) {

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Order> orderPage = orderRepository.findByStatusAndPriceGreaterThan(status, totalPrice, pageable);
        List<Order> orders = orderPage.getContent();

        if (orders.isEmpty()) {
            throw new RuntimeException("No such Order exists!!");
        }

        return orders;

    }

    public Order updateOrderById(String orderId, Order order) {

        Order oldOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No such Order exists!!"));

        oldOrder.setTotalPrice(order.getTotalPrice());
        oldOrder.setQuantity(order.getQuantity());
        oldOrder.setStatus(order.getStatus());

        return orderRepository.save(oldOrder);

    }

    public String deleteOrderById(String orderId) {

        Order orderToDelete = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No such Order exists!!"));

        orderRepository.delete(orderToDelete);

        return "Order with orderId: " + orderId + " deleted successfully!!";

    }
}
