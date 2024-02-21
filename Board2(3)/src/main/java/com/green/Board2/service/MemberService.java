package com.green.Board2.service;


import com.green.Board2.vo.MemberVO;

public interface MemberService {
    //회원 등록
    void joinWith(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);
}
