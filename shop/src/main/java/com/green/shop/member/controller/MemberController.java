package com.green.shop.member.controller;

import com.green.shop.member.service.MemberServiceImpl;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ShortArrayPutAtMetaMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Resource(name = "MemberService")
    private MemberServiceImpl memberService;

    private MemberVO memberVO;

    //회원 가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        //문자열 치환
        //String test1 = "abcabc";
        //test1.replace("a","A"); - AbcAbc

        //연락처 세팅
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",","-"));
        //이메일 세팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",",""));
        //회원 가입 쿼리 실행
        memberService.join(memberVO);
        System.out.println(memberVO);
        return "redirect:/item/list";
    }

    @GetMapping("/loginPage")
    public String goLoginPage(){
        return "content/member/login";
    }

    //로그인
    @PostMapping("/getLogin")
    public String getLogin(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        //로그인 성공 시 세션에 데이터 저장
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        System.out.println(loginInfo);
        return "content/member/login_result";
    }

    //비동기 로그인
    @ResponseBody
    @PostMapping("/loginFetch")
    public String loginFetch(MemberVO memberVO, HttpSession session){
        MemberVO loginInfo = memberService.login(memberVO);

        //로그인 성공 시 세션에 데이터 저장
        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
        }

        return loginInfo == null ? "" : loginInfo.getMemberId();
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");

        return "redirect:/item/list";
    }
}
