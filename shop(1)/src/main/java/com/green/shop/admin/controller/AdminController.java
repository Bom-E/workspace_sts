package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        //카테고리 목록 조회
        List<CategoryVO> categoryList = itemService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("page", 2);

        return "content/admin/reg_item_form";
    }

    //상품 등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, @RequestParam(name = "mainImg")MultipartFile mainImg, @RequestParam(name = "subImages") MultipartFile[] subImages, Model model){

        //--------------------------파일 첨부 기능------------------------------//
        //메인 이미지 하나 업로드, UploadUtil 클래스 참조
        //UploadUtil이 파일 업로드 후 파일명 리턴 받음
        ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);

        //상세 이미지 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImages);

        //--------------------다음에 들어갈 ITEM_CODE 조회-----------------------//
        int itemCode = adminService.selectNextItemCode();

        //-----------------위의 iemCode를 itemVO에 셋팅-----------------------//
        itemVO.setItemCode(itemCode);

        //-------------------상품 이미지 정보 등록 쿼리 실행-----------------------//
        //현재 itemVO에는 상품명, 가격, 상품소개, cateCode

        //이미지 등록 시 채워 줘야 하는 모든 데이터를 갖는 리스트 생성
        mainImgVO.setItemCode(itemCode);

        for(ImgVO img : imgList){
            img.setItemCode(itemCode);
        }
        imgList.add(mainImgVO);
        itemVO.setImgList(imgList);

        System.out.println(itemVO);
        adminService.insertItem(itemVO);

        return "redirect:/admin/regItemForm";
    }

    //구매 목록 조회(관리자 용)
    @RequestMapping("/buyListManage")
    public String buyListManage(SearchBuyVO searchBuyVO, Model model){

        model.addAttribute("page", 1);

        List<BuyVO> buyVOList = adminService.selectBuyList(searchBuyVO);

        model.addAttribute("buyVOList", buyVOList);

        return "content/admin/admin_history";
    }

    //상세구매내역 조회
    @ResponseBody
    @PostMapping("/selectBuyDetail")
    public BuyVO selectBuyDetail(BuyVO buyVO){
        //구매 상세 내역 조회

        BuyVO buyList = adminService.selectBuyDetail(buyVO);


        return buyList;
    }

    //상품 정보 변경
    @GetMapping("/itemStatusChange")
    public String itemStatusChange(Model model, @RequestParam(name = "itemCode", required = false, defaultValue = "0")int itemCode){

        model.addAttribute("page", 3);

        List<ItemVO> itemList = adminService.itemStatusChange();

        model.addAttribute("itemList", itemList);

        model.addAttribute("updateItemCode", itemCode);

        for(ItemVO item : itemList){
            System.out.println(item);
        }

        return "content/admin/item_status_change";
    }

    //상품 정보 변경 화면의 상품 목록 테이블의 행 클릭 시
    //상품의 상세 정보를 조회 하는 비동기
    @PostMapping("/selectItemDetail")
    @ResponseBody
    public Map<String, Object> selectItemDetail(@RequestParam (name = "itemCode")int itemCode){

        //상품 상세 정보 조회
        ItemVO itemDetail = adminService.selectItemDetail(itemCode);

        //카테고리 목록 조회
        List<CategoryVO> cateList = itemService.selectCategoryList();

        //두 데이터를 담을 수 있는 map 객체 생성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", cateList);

        return map;
    }

    //상품 정보 변경
    @PostMapping("/updateItemDetail")
    public String updateItemDetail(ItemVO itemVO){

        adminService.updateItemDetail(itemVO);

        return "redirect:/admin/itemStatusChange?itemCode=" + itemVO.getItemCode();
    }

}
