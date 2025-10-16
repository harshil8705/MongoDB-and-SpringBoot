package com.harshil.mongodbTut.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "orders")
@CompoundIndex(name = "quantity_status_index", def = "{ 'quantity': 1, 'status': 1 }")
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

}
