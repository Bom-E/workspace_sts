package com.green.shop.cart.controller;

import com.green.shop.cart.sevice.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name = "cartService")
    CartServiceImpl cartService;

    //장바구니 상품 등록
    @ResponseBody
    @PostMapping("/insertCart")
    public void goCart(CartVO cartVO, HttpSession session){

        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());

        cartService.insertCart(cartVO);

    }

    //장바구니 목록 조회
    @GetMapping("/list")
    public String list(HttpSession session, Model model, @RequestParam(name = "page", required = false, defaultValue = "cartList") String page){

        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());

        model.addAttribute("cartList", cartList);

        //총 가격을 계산한 후 html로 전달
        int sum = 0;
        for(CartViewVO e: cartList){
            sum += e.getTotalPrice();
        }

        //model.addAttribute("finalPrice", sum); 계산은 했지만 주석 처리 하면 html로 안 뿌림

        //함께 열리는 html들은 다 같이 데이터를 공유
        //user_side에서 넘어오는 page 여기에 cartList도 들어가 있다.
        model.addAttribute("page", page);

        return "content/cart/cart_list";
    }

    //장바구니 아이템 삭제
    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam(name = "cartCode")int cartCode){
        System.out.println(cartCode);
        cartService.deleteItem(cartCode);
        return "redirect:/cart/list";
    }

    //장바구니에 담긴 상품 수량 변경
    @ResponseBody
    @PostMapping("/updateCartCnt")
    public void updateCartCnt(CartVO cartVO){
        cartService.updateCartCnt(cartVO);
        System.out.println(cartVO);
    }

    @GetMapping("/deleteItems")
    public String deleteItems(CartVO cartVO){
        //js에서 컨트롤러로 데이터 받을 때 배열은 리스트로도 받기 가능함
        //js는 배열을 바로 자바로 던질 수 있음.
        cartService.deleteItems(cartVO);
        return "redirect:/cart/list";
    }

}
