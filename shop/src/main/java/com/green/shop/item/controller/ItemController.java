package com.green.shop.item.controller;

import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//상품과 관련 된 모든 페이지 이동을 처리 하는 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model){

        //상품 전체 목록 조회
        model.addAttribute("itemList", itemService.itemList());

        return "content/item/item_list";
    }

}
