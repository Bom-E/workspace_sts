package com.green.Car.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SalesInfoVO {
    private int salesNumber;
    private String customerName;
    private String customerTel;
    private String salesColor;
    private String salesDate;
    private int carNumber;
    private CarInfoVO carInfo;
}
