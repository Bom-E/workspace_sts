package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list = stuService.selectStuList();

        //조회한 목록을 html로 전달(html로 넘길 때 명칭 명명)
        model.addAttribute("stuList", list);

        return "stu_list";
    }

    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);
        return "redirect:/";
        //컨트롤러로("/") 다시 보내기 그러고 /의 메소드 실행
    }

    @GetMapping("/stuDetail")
    //특정 데이터 가져와서 다음 html로 전달
    public String stuDetail(@RequestParam(name = "stuNo") int stuNo, Model model){
        //학생의 상세정보 조회
        StuVO stu = stuService.selectStu(stuNo);
        //조회한 데이터를 html로 전달
        model.addAttribute("stuInfo",stu);

    return "student_detail";
    }

    @GetMapping("/deleteStu")
    public String deleteStu(StuVO stuVO){
        //@RequestParam(name = "stuNo") int stuNo
        //조회 된 학생의 정보 삭제
        stuService.deleteStu(stuVO.getStuNo());
        //삭제 된 뒤 나머지 데이터를 html로 전달
        return "redirect:/";
    }

}
