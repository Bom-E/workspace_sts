package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //카테고리 목록 조회
    @Override
    public List<CategoryVO> selectCategoryList() {
        return sqlSession.selectList("itemMapper.selectCategoryList");
    }

    //상품 목록 조회
    @Override
    public List<ItemVO> itemList() {
        return sqlSession.selectList("itemMapper.itemList");
    }
}
