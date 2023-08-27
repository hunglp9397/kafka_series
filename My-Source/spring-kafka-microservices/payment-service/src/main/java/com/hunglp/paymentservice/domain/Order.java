package com.hunglp.paymentservice.domain;

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


}
