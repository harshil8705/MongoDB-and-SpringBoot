package com.harshil.mongodbTut.repository;

import com.harshil.mongodbTut.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByStatusAndQuantityGreaterThan(String status, Integer quantity);

    @Query("{ 'status': ?0, 'totalPrice': { '$gt': ?1 } }")
    Page<Order> findByStatusAndPriceGreaterThan(String status, Double totalPrice, Pageable pageable);

    @Query(value = "{ '_id': ?0 }", fields = "{ 'products': 0 }")
    Optional<Order> findOrderWithoutProductsById(String orderId);

}
