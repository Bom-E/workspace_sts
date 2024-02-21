package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록 및 이미지 등록
    void insertItem(ItemVO itemVO);

    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();

    //구매 목록 조회(관리자용)
    List<BuyVO> selectBuyList(SearchBuyVO searchBuyVO);

    //구매 목록 팝업 모달
    BuyVO selectBuyDetail(BuyVO buyVO);

    //상품 정보 변경 화면에서 상품 목록 조회
    List<ItemVO> itemStatusChange();

    //상품 목록 상세 정보 조회
    ItemVO selectItemDetail(int itemCode);

    //상품 목록 상세 정보 수정
    void updateItemDetail(ItemVO itemVO);
}
