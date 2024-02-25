package com.green.Car.service;

import com.green.Car.vo.CarInfoVO;
import com.green.Car.vo.SalesInfoVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertCarInfo(CarInfoVO carInfo) {
        sqlSession.insert("carInfoMapper.insertCarInfo", carInfo);
    }

    @Override
    public List<CarInfoVO> selectCarInfo() {
        return sqlSession.selectList("carInfoMapper.selectCarInfo");
    }

    @Override
    public void insertSellInfo(SalesInfoVO salesInfoVO) {
        sqlSession.insert("sellInfoMapper.insertSellInfo", salesInfoVO);
    }

    @Override
    public List<SalesInfoVO> selectAllSellInfo() {
        return sqlSession.selectList("sellInfoMapper.selectAllSellInfo");
    }
}
