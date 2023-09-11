package com.hunglp.config.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CallingInfo {

    private Integer id;

    private String caller;

    private String called;

    private Date startTime;

    private Date endTime;



}
