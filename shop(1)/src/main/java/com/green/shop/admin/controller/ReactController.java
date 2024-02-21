package com.green.shop.admin.controller;

import com.green.shop.item.service.ItemService;
import com.green.shop.item.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ReactController {
    @Resource(name = "itemService")
    private ItemService itemService;

    @GetMapping("/test1")
    public String test1(){

        return "안녕하세요.";
    }

    @GetMapping("/test2")
    public List<CategoryVO> test2(){

        return itemService.selectCategoryList();
    }

}
