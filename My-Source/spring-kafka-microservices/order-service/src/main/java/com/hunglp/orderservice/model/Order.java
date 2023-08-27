package com.hunglp.orderservice.model;


import lombok.Data;

@Data
public class Order {

    private Long id;

    private Long customerId;

    private Long productId;

    private int productQuantity;

    private int price;

    private String status;

    private String source;

    public Order(Long id, Long customerId, Long productId, int productQuantity, int price) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.price = price;
        this.status = "NEW";
    }


}
