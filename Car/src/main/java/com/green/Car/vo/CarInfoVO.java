package com.green.Car.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarInfoVO {
    private int carNumber;
    private String carName;
    private int carPrice;
    private int carMakerNumber;
    private String carMakerStr;
}
