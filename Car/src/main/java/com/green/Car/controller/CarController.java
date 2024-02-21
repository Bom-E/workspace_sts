package com.green.Car.controller;

import com.green.Car.service.CarService;
import com.green.Car.service.CarServiceImpl;
import com.green.Car.vo.CarInfoVO;
import com.green.Car.vo.SalesInfoVO;
import jakarta.annotation.Resource;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ShortArrayPutAtMetaMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Resource(name = "carService")
        private CarService carService;

    @GetMapping("/main")
    public String carMain(@RequestParam(name = "page", required = false, defaultValue = "main")String page, Model model){

        model.addAttribute("page", page);

        return "content/admin/main";
    }

    @GetMapping("/carManagement")
    public String carManagement(@RequestParam(name = "page", required = false, defaultValue = "carManagement")String page, Model model
                                ){

        model.addAttribute("page", page);

        List<CarInfoVO> carInfoList = carService.selectCarInfo();

        model.addAttribute("carInfoList", carInfoList);

        return "content/admin/car_management";
    }

    //차 정보 등록 시 동기 통신으로 insert 후 select
    @PostMapping("/insertCarInfo")
    public String insertCarInfo(CarInfoVO carInfoVO){

        carService.insertCarInfo(carInfoVO);

        return "redirect:/car/carManagement";
    }

    @GetMapping("/goSellInfoPage")
    public String goSellInfoPage(@RequestParam(name = "page", required = false, defaultValue = "goSellInfoPage")String page, Model model){

        model.addAttribute("page", page);

        return "content/admin/go_sell_info_page";
    }

    @PostMapping("/insertSellInfo")
    public String insertSellInfo(SalesInfoVO salesInfoVO){

        carService.insertSellInfo(salesInfoVO);

        return "content/admin/select_all_sell_info";
    }

    @GetMapping("/selectAllSellInfo")
    public String selectAllSellInfo(Model model){



        return "";
    }
}
