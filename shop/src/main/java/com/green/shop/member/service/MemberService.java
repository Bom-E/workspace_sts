package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;

public interface MemberService {

    void join(MemberVO memberVO);

    MemberVO login(MemberVO memberVO);
}
