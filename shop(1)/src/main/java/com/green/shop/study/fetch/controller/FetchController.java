package com.green.shop.study.fetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.shop.item.vo.ImgVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String main(){
        return "test/fetch/main";
    }

    //넘어오는 테이터를 받을 때 사용하는 어노테이션
    //@RequestParam : url에 데이터가 함께 넘어올 때 사용
    // ex1 ) localhost:8081/aaa?a=b (GET 방식 주소창에 데이터가 보일 때 (a태그))
    // ex2 ) form(POST 방식) 태그를 사용 (우리 눈에 안 보일 뿐, url에 데이터가 담겨 옴)
    //@RequestBody : url이 아닌 body 영역에 데이터가 담겨서 올 때
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch1(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 메소드 실행~");
        System.out.println(memberVO);
    }

    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 메소드 실행~");
        System.out.println(data);
        System.out.println(data.get("id"));
        System.out.println(data.get("name"));
        System.out.println(data.get("age"));

    }

    //자바스크립트에서 배열이 넘어오면 ArrayList 로 받을 수 있음
    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody ArrayList<MemberVO> list){
        System.out.println("fetch3 메소드 실행~");
        System.out.println(list);
    }

    @ResponseBody
    @PostMapping("/fetch4")
    public void fetch4(@RequestBody HashMap<String, Object> map){
        System.out.println("fetch4 메소드 실행~");
        System.out.println(map);

        //1. cartCode 출력
        int cartCode = (int) map.get("cartCode");
        System.out.println(cartCode);

        //2. memberId 출력
        HashMap<String, String> memberInfo = (HashMap<String, String>) map.get("memberInfo");
        String memberId = memberInfo.get("memberId");
        System.out.println(memberId);

        //3. imgCode 출력
        Map<String, Object> itemInfo = (Map<String, Object>) map.get("itemInfo");
        List<Object> imgList = (List<Object>) itemInfo.get("imgList");
        System.out.println(imgList);
        Map<String, Object> img = (Map<String, Object>)imgList.get(1);
        System.out.println(img);
        int imgCode = (int) img.get("imgCode");
        System.out.println(imgCode);

        //////////////////////////////////////////////////////////////////////////

        //map 데이터를 vo객체에 매핑하기
        ObjectMapper mapper = new ObjectMapper();
        MapDataVO data = mapper.convertValue(map, MapDataVO.class);
        System.out.println(data);

    }

}