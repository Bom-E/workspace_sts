package com.green.shop.item.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//상품과 관련된 모든 페이지 이동 처리 컨트롤러
@Controller
@RequestMapping("/item")
public class ItemController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;
    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model, ItemVO itemVO){
        //상품 목록 조회
        List<ItemVO> itemList = itemService.selectItemList(itemVO);
        model.addAttribute("itemList", itemList);

        return "content/item/item_list";
    }

    //상품 상세 페이지
    @GetMapping("/itemDetail")
    public String itemDetail(@RequestParam(name = "itemCode")int itemCode, Model model){
        //상세 정보 조회
        ItemVO item = itemService.selectItemDetail(itemCode);
        model.addAttribute("item", item);
        System.out.println(item);

        return "content/item/item_detail";
    }
}







