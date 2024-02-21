package com.green.Board2.controller;

import com.green.Board2.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {

    //localhost:8081/test/
    @GetMapping("/page1")
    public String page1(HttpSession session){
                            // 문자열 고정, Object
        //세션에 데이터 저장 하기/ 명명, 저장할 값
        session.setAttribute("name", "java");
        session.setAttribute("age", 20);
        session.setAttribute("member", new MemberVO());

        //세션 유지시간 설정 : 초단위, 아무것도 안 했을 때 10 초 후 꺼짐(마우스만 움직이는 것도 포함)
        session.setMaxInactiveInterval(60 * 30); //30분
        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인
        String name = (String)session.getAttribute("name");
        int age = (int)session.getAttribute("age");
        MemberVO vo = (MemberVO)session.getAttribute("member");

        //세션 데이터 삭제
        session.removeAttribute("age");

        //모든 세션 데이터 지우기
        session.invalidate();

        return "page2";
    }

    @GetMapping("page3")
    public String page3(){
        return "page3";
    }
}
