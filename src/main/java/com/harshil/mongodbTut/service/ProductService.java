package com.harshil.mongodbTut.service;

import com.harshil.mongodbTut.entity.Order;
import com.harshil.mongodbTut.entity.Product;
import com.harshil.mongodbTut.repository.OrderRepository;
import com.harshil.mongodbTut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Product addProductByOrderId(String orderId, Product product) {

        Product productToAdd = Product.builder()
                .price(product.getPrice())
                .category(product.getCategory())
                .name(product.getName())
                .build();
        productToAdd = productRepository.save(productToAdd);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("No such Order exists!!"));

        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            order.setProducts(new ArrayList<>(List.of(productToAdd)));
        } else {
            order.getProducts().add(productToAdd);
        }

        orderRepository.save(order);

        return productToAdd;

    }

    public Product getProductByProductId(String productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No such Product exists!!"));

    }


    public Product updateProductByProductId(String productId, Product product) {

        Product oldProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No such Product exists!!"));

        oldProduct.setCategory(product.getCategory());
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());

        return productRepository.save(oldProduct);

    }

    public String deleteProductByProductId(String productId) {

        Product productToDelete = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No such Product exists!!"));

        productRepository.delete(productToDelete);

        return "Product with productId: " + productId + " has been removed successfully.";

    }

}
