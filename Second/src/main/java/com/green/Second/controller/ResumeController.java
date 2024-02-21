package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    //처음에는 주소를 타이핑 해서 들어가야 해서 무조건 get이 들어옴
    @GetMapping("/r1")
    public String main(){
        return "resume_reg";
    }

    @PostMapping("/r2")
    public String resumeInfo(ResumeVO resumeVO){
        System.out.println(resumeVO);
        return "/resume_info";
    }

//    @PostMapping("/resume_confirm")
//    public String resume_confirm(@RequestParam(name = "name") String name, @RequestParam(name = "tel") String tel,@RequestParam(name = "edu") String edu, @RequestParam(name = "schoolname") String schoolname, @RequestParam(name = "newold") String newold, @RequestParam(name = "자격증명") String 자격증명, @RequestParam(name = "취득일자") String 취득일자, @RequestParam(name = "발행기관") String 발행기관, @RequestParam(name = "회사명") String 회사명, @RequestParam(name = "담당업무") String 담당업무, @RequestParam(name = "경력기간_년") String 년, @RequestParam(name = "경력기간_개월") String 개월, @RequestParam(name = "intro") String intro, Model model){
//
//        model.addAttribute("name", name);
//        model.addAttribute("tel", tel);
//        model.addAttribute("edu", edu);
//        model.addAttribute("schoolname", schoolname);
//        model.addAttribute("newold", newold);
//        model.addAttribute("자격증명", 자격증명);
//        model.addAttribute("취득일자", 취득일자);
//        model.addAttribute("발행기관", 발행기관);
//        model.addAttribute("회사명", 회사명);
//        model.addAttribute("담당업무", 담당업무);
//        model.addAttribute("경력기간_년", 년);
//        model.addAttribute("경력기간_개월", 개월);
//        model.addAttribute("intro", intro);
//        System.out.println(edu);
//        System.out.println(schoolname);
//        System.out.println(newold);
//        System.out.println(자격증명);
//        System.out.println(취득일자);
//        System.out.println(발행기관);
//        System.out.println(회사명);
//        System.out.println(담당업무);
//        System.out.println(개월);
//        System.out.println(년);
//        System.out.println(intro);
//        return "/resume_confirm";
//    }

    //VO 활용:
    @PostMapping("/r3")
    public String resume_confirm(ResumeVO resumeVO){
        //커맨드 객체 : ResumeVO/ RequestParam은 커맨드 객체가 아님.
        System.out.println(resumeVO);
        return "resume_confirm";
    }
}
