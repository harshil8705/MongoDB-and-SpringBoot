package com.harshil.mongodbTut;

import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongodbTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() {

        Order order = Order.builder()
                .status("Pending")
                .quantity(10)
                .totalPrice(2999.00)
                .build();

        order = orderRepository.insert(order);

        System.out.println(order);

    }

}
