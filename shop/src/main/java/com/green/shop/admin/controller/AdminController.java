package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminServiceImpl adminService;
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    //상품등록 페이지 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        //카테고리 목록 조회
        List<CategoryVO> categoryList = itemService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);

        return "content/admin/reg_item_form";
    }

    //상품등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO){
        //상품 등록 쿼리 실행
        adminService.insertItem(itemVO);
        return "redirect:/admin/regItemForm";
    }
}
