package com.green.Second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//@Controller : 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring에서 인식 하기 위함
@Controller //어노테이션
public class MemberController {

    //@GetMapping, @PostMapping
    //페이지 접속 정보
    //소괄호 안의 글자와 localhost:8081 뒤의 글자가 일치하면
    //해당 메소드를 실행
    //@PostMapping : 페이지 이동 방법 중에 form 태그로 이동 하면서
    //form 태그의 method 속성 값이 post 일때만 실행
    //@GetMapping :
    //1. form 태그의 method 속성 값이 get 일 때.
    //2. a 태그로 페이지가 이동될 때.
    //3. 주소창에 직접 url을 입력 했을 때.
    @GetMapping("/") //뒤에 아무것도 안 붙고 싶으면 "/" 만 넣으면 됨.
    public String main(){
        //리턴되는 문자열은 마지막에 실행되는 html 파일명!
        //html 파일은 반드시 templates 폴더 안에 존재!
        return "first"; //first.html 실행!
    }

    //@RequestParam 어노테이션으로 html에서 넘어오는 데이터를 전달 받을 수 있다.
    //해당 어노테이션 속성으로는 name, required, defaultValue가 있음
    //name : html에서 넘어오는 데이터의 이름
    //required : 넘어오는 데이터가 필수 데이터인지 설정 true : 무조건 넘어옴. false : 일 수도 있고 아닐 수도 있고
    //required 속성 값을 적지 않으면 default 값은 true가 되어서 그런 상황이 아니면 false 넣어주기
    //defaultValue : 데이터가 넘어오지 않을 때 설정 되는 기본 값

    //html로 데이터를 전달 하기 위해서는 메소드의 매개변수에 Model(interface)의 객체를 선언
    @GetMapping("/second")
    //required를 false로 만들면 addr이 없어도 됨 그럼 addr은 null이 됨
    public String second(@RequestParam(name = "addr", required = false) String address,
                         @RequestParam(name = "age", required = false, defaultValue = "1") int age
                        , Model model){
        System.out.println("addr = " + address);
        System.out.println("age = " + age);

        //html로 데이터 전달하기. 이름은 마음대로 넣어도 됨. (넘어가는 데이터의 이름, 실제 넘어가는 데이터.)
        model.addAttribute("addr" , address);
        model.addAttribute("age" , age);
        model.addAttribute("name" , "홍길동");

        return "second";
    }


}
