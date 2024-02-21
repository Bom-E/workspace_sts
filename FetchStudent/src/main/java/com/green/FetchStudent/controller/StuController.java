package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StuController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    @GetMapping("/")
    public String main(Model model, StuVO stuVO){
        //학급 목록 데이터 조회
        model.addAttribute("classList",stuService.selectClassList());
        //학생 목록 데이터 조회
        model.addAttribute("selectStu", stuService.selectStu(stuVO));
        return "stu_manage";
    }

    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList = stuService.selectStu(stuVO);

        return stuList;
    }

    @ResponseBody
    @PostMapping("/detail")
    public DetailVO detail(@RequestParam(name = "stuNum")int stuNum) {
        //클릭한 학생의 상세 정보 조회
        StuVO stuInfo = stuService.selectStuInfo(stuNum);

        //클릭한 학생의 점수 정보도 조회

        ScoreVO scoreInfo = stuService.selectScoreInfo(stuNum);

        //조회한 데이터를 가지고 js로 이동
        DetailVO result = new DetailVO();
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);

        return result;
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);


    }
}