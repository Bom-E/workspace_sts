package com.green.Car.service;

import com.green.Car.vo.CarInfoVO;
import com.green.Car.vo.SalesInfoVO;

import java.util.List;

public interface CarService {

    //차 정보 등록
    void insertCarInfo(CarInfoVO carInfo);

    //차 정보 조회
    List<CarInfoVO> selectCarInfo();

    //구매자 정보 등록
    void insertSellInfo(SalesInfoVO salesInfoVO);
}
