package com.harshil.mongodbTut;

import com.harshil.mongodbTut.entity.Address;
import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.entity.Product;
import com.harshil.mongodbTut.repository.OrderRepository;
import com.harshil.mongodbTut.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MongodbTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateOrder() {

        List<Product> productList = new ArrayList<>();

            Product laptop = Product.builder()
                    .name("HP ZBook Z5")
                    .category("Laptop")
                    .price(149999.00)
                    .build();
            laptop = productRepository.save(laptop);
            productList.add(laptop);

            Product phone = Product.builder()
                    .name("IPhone 17 Pro Max Ultra")
                    .category("Phone")
                    .price(104999.00)
                    .build();
            phone = productRepository.save(phone);
            productList.add(phone);


        for (int i = 1; i <= 10; i++) {

            Order order = Order.builder()
                    .status("Delivered")
                    .quantity(3*i)
                    .totalPrice(productList.stream().mapToDouble(Product::getPrice).sum() * i)
                    .products(productList)
                    .address(Address.builder()
                            .line1("Line1 Address")
                            .city("Surendranagar")
                            .state("Gujarat")
                            .zipcode("363020")
                            .build())
                    .build();
            order = orderRepository.save(order);
            System.out.println(order);

        }

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
