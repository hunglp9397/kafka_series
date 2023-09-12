package com.hunglp.springkafkasendussd.model;


import lombok.Data;

@Data
public class UssdSurvey {

    private Integer id;

    private String caller;

    private String called;

    private String content;

    private Integer resultCode; // 1 : La Spam, -1 : Ko phai spam, 0 : bo qua
}