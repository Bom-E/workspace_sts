package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;

public interface MemberService {

    //회원 가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(String memberId);

}
