package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.sevice.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/buy")
@Controller
public class BuyController {
    @Resource(name = "cartService")
    private CartServiceImpl cartService;
    @Resource(name = "buyService")
    private BuyServiceImpl buyService;

    //선택 구매
    @GetMapping("/buyCarts")
    public String buyInfo(CartVO cartVO, HttpSession session){
        //구매 상세 테이블에 insert 할 ITEM_CODE, BUY_CNT, TOTAL_PRICE 조회
        List<CartViewVO> cartViewList = cartService.selectCartListForBuy(cartVO);

        //구매 정보 테이블에 들어 갈 BUY_PRICE(구매 총 가격) 계산
        int buyPrice = 0;
        for(CartViewVO e : cartViewList){
            buyPrice += e.getTotalPrice();
        }

        //세션 정보 취득
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        ///다음에 들어갈 BUY_CODE 결정
        int buyCode = buyService.selectNextBuyCode();

        //구매정보 및 구매상세테이블에 insert!
        BuyVO buyVO = new BuyVO();
        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(memberId);
        buyVO.setBuyPrice(buyPrice);
        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for(CartViewVO cartView : cartViewList){
            BuyDetailVO vo = new BuyDetailVO();
            vo.setItemCode(cartView.getItemCode());
            vo.setBuyCnt(cartView.getCartCnt());
            vo.setTotalPrice(cartView.getTotalPrice());
            buyDetailList.add(vo);
        }

        buyVO.setBuyDetailList(buyDetailList);
        buyService.insertBuys(buyVO, cartVO);

        return "redirect:/item/list";
    }

    //상세 페이지에서 바로 구매
    @PostMapping("/insertBuy")
    public String insertBuy(BuyVO buyVO, BuyDetailVO buyDetailVO, HttpSession session){

        //다음에 들어가야 하는 BUY_CODE 값을 조회
        int buyCode = buyService.selectNextBuyCode();
        buyVO.setBuyCode(buyCode);

        //memberId 세팅
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        buyVO.setMemberId(loginInfo.getMemberId());

        //값 세팅
        buyVO.setBuyPrice(buyDetailVO.getTotalPrice());

        //buyDetail의 buyCode 세팅
        buyDetailVO.setBuyCode(buyCode);

        System.out.println(buyVO);
        System.out.println(buyDetailVO);
        buyService.insertBuy(buyVO, buyDetailVO);

        return "redirect:/item/list";
    }

    //구매 이력 페이지
    @GetMapping("/history")
    public String history(@RequestParam(name = "page", required = false, defaultValue = "history")String page, Model model, HttpSession session){

        model.addAttribute("page", page);

        //로그인 정보
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        //구매 목록 조회
        List<BuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());
        for(BuyVO e : buyList){
            System.out.println(e);
        }

        model.addAttribute("buyList", buyList);

        return "content/buy/history";
    }
}