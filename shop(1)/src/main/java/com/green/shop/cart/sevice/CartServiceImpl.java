package com.green.shop.cart.sevice;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertCart(CartVO cartVO) {

        //현재 내 장바구니에 지금 추가하려는 상품이 있는지 확인
        int cnt = sqlSession.selectOne("cartMapper.getCntOfCart", cartVO);

        if(cnt == 0){
            //존재하지 않으면 장바구니에 추가
            sqlSession.insert("cartMapper.insertCart", cartVO);
        } else {
            //존재하면 수량만 변경
            sqlSession.update("cartMapper.plusCartCnt", cartVO);
        }
    }

    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memberId);
    }

    @Override
    public void deleteItem(int cartCode) {
        sqlSession.delete("cartMapper.deleteItem", cartCode);
    }

    @Override
    public void updateCartCnt(CartVO cartVO) {
        sqlSession.update("cartMapper.updateCartCnt", cartVO);
    }

    @Override
    public void deleteItems(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteItems", cartVO);
    }

    @Override
    public List<CartViewVO> selectCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectCartListForBuy", cartVO);
    }
}
