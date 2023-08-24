package com.hunglp.carlocationproducer.model;

import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Vehicle {
    public Integer id;
    public int locationId;
    public String vin;
    public String numberPlate;
    public Position position;
    public double fuel;
    public String model;
}
