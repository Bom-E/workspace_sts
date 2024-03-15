package com.green.shop.security;

import com.green.shop.member.service.MemberService;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 스프링 시큐리티가 제공하는 로그인 기능을 구현하는 클래스
// UserDetailsService 인터페이스를 상속해서 구현
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "memberService")
    private MemberService memberService;

    // 시큐리티가 로그인 프로세스를 진행하면 가장 먼저 호출되는 메소드
    // 실제 로그인을 처리하는 메소드는 아님!
    // 시큐리티가 로그인을 처리할 수 있도록
    // 로그인 정보를 시큐리티에게 전달하는 역할
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인을 시도하는 유저의 정보(id, pw, roll)를 조회
        MemberVO loginInfo = memberService.login(username);

        // 로그인 하려는 유저 정보를 시큐리티에게 넘겨 줌
        User user = (User) User.builder()
                        .username(loginInfo.getMemberId())
                        .password("{noop}" + loginInfo.getMemberPw())
                        .roles(loginInfo.getMemberRoll())
                        .build();

        return user;
    }
}
