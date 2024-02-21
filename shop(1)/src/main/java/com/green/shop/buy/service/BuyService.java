package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {
    //다음에 들어갈 BUY_CODE 조회
    int selectNextBuyCode();

    //장바구니 상품 구매
    void insertBuys(BuyVO buyVO, CartVO cartVO);

    //상세 페이지에서 바로 구매
    void insertBuy(BuyVO buyVO, BuyDetailVO buyDetail);

    //구매 목록 조회(사용자용)
    List<BuyVO> selectBuyList(String memberId);
}
