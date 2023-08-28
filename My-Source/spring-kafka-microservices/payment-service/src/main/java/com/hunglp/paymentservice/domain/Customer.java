package com.hunglp.paymentservice.domain;


import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "amount_available")
    private int amountAvailable;

    @Column(name = "amount_reserved")
    private int amountReserved;
}
