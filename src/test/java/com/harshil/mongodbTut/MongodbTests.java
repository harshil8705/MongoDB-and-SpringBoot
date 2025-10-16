package com.harshil.mongodbTut;

import com.harshil.mongodbTut.entity.Address;
import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class MongodbTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testCreateOrder() {

        for (int i = 1; i <= 10; i++) {

            Order order = Order.builder()
                    .status("Delivered")
                    .quantity(3*i)
                    .totalPrice(3000.00 * i)
                    .address(Address.builder()
                            .line1("Line1 Address")
                            .city("Surendranagar")
                            .state("Gujarat")
                            .zipcode("363020")
                            .build())
                    .build();

            order = orderRepository.insert(order);

        }

        Order order = Order.builder()
                .status("Processing")
                .quantity(7)
                .totalPrice(10500.00)
                .build();

        order = orderRepository.insert(order);

        System.out.println(order);

    }

    @Test
    public void getOrders() {

        Pageable pageable = PageRequest.of(0, 20, Sort.by("totalPrice").descending());

        List<Order> orders = orderRepository.findAll(pageable).toList();

        if (orders.isEmpty()) {
            System.out.println("No such Order exists.");
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
            }
        }
    }

}
