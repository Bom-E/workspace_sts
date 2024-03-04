package com.green.Car.controller;

import com.green.Car.service.CarService;
import com.green.Car.service.CarServiceImpl;
import com.green.Car.vo.CarInfoVO;
import com.green.Car.vo.SalesInfoVO;
import jakarta.annotation.Resource;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ShortArrayPutAtMetaMethod;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Resource(name = "carService")
        private CarService carService;

    //메인 페이지
    @GetMapping("/main")
    public String carMain(@RequestParam(name = "page", required = false, defaultValue = "main")String page, Model model){

        model.addAttribute("page", page);

        return "content/admin/main";
    }

    //차량 관리 시스템
    @GetMapping("/carManagement")
    public String carManagement(@RequestParam(name = "page", required = false, defaultValue = "carManagement")String page, Model model){

        model.addAttribute("page", page);

        List<CarInfoVO> carInfoList = carService.selectCarInfo();

        model.addAttribute("carInfoList", carInfoList);

        return "content/admin/car_management";
    }

    //차 정보 등록 및 조회
    @PostMapping("/insertCarInfo")
    public String insertCarInfo(CarInfoVO carInfoVO){

        carService.insertCarInfo(carInfoVO);

        return "redirect:/car/carManagement";
    }

    //구매자 정보 등록 페이지로 이동
    @GetMapping("/goSellPage")
    public String goSellPage(@RequestParam(name = "page", required = false, defaultValue = "goSellPage")String page, Model model){

        model.addAttribute("page", page);

        //셀렉트 박스의 모델명 조회
        List<CarInfoVO> carsInfo = carService.selectCarInfo();
        model.addAttribute("carsInfo", carsInfo);


        return "content/admin/go_sell_page";
    }

    //구매자 정보 등록
    @PostMapping("/insertSellInfo")
    public String insertSellInfo(SalesInfoVO salesInfoVO, CarInfoVO carInfoVO){

        carService.insertSellInfo(salesInfoVO);

        System.out.println(salesInfoVO);

        return "redirect:/car/selectAllSellInfo";
    }

    //모든 구매 정보 조회
    @GetMapping("/selectAllSellInfo")
    public String selectAllSellInfo(Model model, @RequestParam(name = "page", required = false, defaultValue = "selectAllSellInfo") String page){

        List<SalesInfoVO> salesInfo = carService.selectAllSellInfo();

        model.addAttribute("salesInfo", salesInfo);

        model.addAttribute("page", page);

        return "content/admin/select_all_sell_info";
    }
}
