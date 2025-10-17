package com.harshil.mongodbTut.controller;

import com.harshil.mongodbTut.entity.Product;
import com.harshil.mongodbTut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add/product/orderId/{orderId}")
    public ResponseEntity<Product> addProductByOrderId(
            @PathVariable String orderId,
            @RequestBody Product product
    ) {

        return new ResponseEntity<>(productService.addProductByOrderId(orderId, product), HttpStatus.OK);

    }

    @GetMapping("/get/product/productId/{productId}")
    public ResponseEntity<Product> getProductByProductId(
            @PathVariable String productId
    ) {

        return new ResponseEntity<>(productService.getProductByProductId(productId), HttpStatus.FOUND);

    }

    @PutMapping("/update/product/productId/{productId}")
    public ResponseEntity<Product> updateProductByProductId(
            @PathVariable String productId,
            @RequestBody Product product
    ) {

        return new ResponseEntity<>(productService.updateProductByProductId(productId, product), HttpStatus.OK);

    }

    @DeleteMapping("/delete/product/productId/{productId}")
    public ResponseEntity<String> deleteProductByProductId(
            @PathVariable String productId
    ) {

        return new ResponseEntity<>(productService.deleteProductByProductId(productId), HttpStatus.OK);

    }

}
