package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    @Transactional(rollbackFor = Exception.class)
    //Transactional 어노테이션이 붙어 있는 메소드 내부의 쿼리 실행은
    //모든 쿼리가 실행 성공 시 커밋!
    //쿼리 중 하나라도 실패 시 롤백!
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    //구매 목록 조회(관리자용)
    @Override
    public List<BuyVO> selectBuyList(SearchBuyVO searchBuyVO) {
        return sqlSession.selectList("adminMapper.selectBuyList", searchBuyVO);
    }

    @Override
    public BuyVO selectBuyDetail(BuyVO buyVO) {
        return sqlSession.selectOne("adminMapper.selectBuyDetail", buyVO);
    }

    //상품 정보 변경 화면에서 상품 목록 조회
    @Override
    public List<ItemVO> itemStatusChange() {
        return sqlSession.selectList("adminMapper.itemStatusChange");
    }

    @Override
    public ItemVO selectItemDetail(int itemCode) {
        return sqlSession.selectOne("adminMapper.selectItemDetail", itemCode);
    }

    @Override
    public void updateItemDetail(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateItemDetail", itemVO);
    }


}
