package com.hunglp.springkafkasendussd.model;


import lombok.Data;

@Data
public class UssdMsg {

    private Long id;
    private String sender;
    private String receiver;
    private int responseCode; // 1, 0 , -1

}
