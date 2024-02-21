package com.green.shop.cart.sevice;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.ArrayList;
import java.util.List;

public interface CartService {

    // 장바구니 상품 등록
    void insertCart(CartVO cartVO);

    //장바구니 목록 조회
    List<CartViewVO> selectCartList(String memberId);

    void deleteItem(int cartCode);

    void updateCartCnt(CartVO cartVO);

    void deleteItems(CartVO cartVO);

    //장바구니에 담긴 상품 구매를 위한 장바구니 목록 조회
    List<CartViewVO> selectCartListForBuy(CartVO cartVO);

}
