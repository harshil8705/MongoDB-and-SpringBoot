package com.harshil.mongodbTut.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "orders")
@CompoundIndex(name = "quantity_status_index", def = "{ 'quantity': 1, 'status': 1 }")
@CompoundIndex(name = "address_city_index", def = "{ 'address.city': 1 }")
public class Order {

    @Id
    private String id;

    private Integer quantity;
    private Double totalPrice;

    @Indexed
    private String status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Indexed
    private Address address;

    @DBRef(lazy = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Product> products;

}
