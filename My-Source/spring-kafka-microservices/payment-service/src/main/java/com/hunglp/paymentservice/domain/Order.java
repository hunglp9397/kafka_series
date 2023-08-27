package com.hunglp.paymentservice.domain;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private Long customerId;

    private Long productId;

    private int productCount;

    private int price;

    private String status;

    private String source;

    public Order(Long id, Long customerId, Long productId, int productCount, int price) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.productCount = productCount;
        this.price = price;
        this.status = "NEW";
    }
}
