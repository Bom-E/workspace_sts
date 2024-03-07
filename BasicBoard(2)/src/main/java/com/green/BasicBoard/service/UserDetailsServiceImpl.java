package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.MemberVO;
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
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    // 시큐리티가 알아서 로그인 할 때 호출하는 메소드
    // 매개변수로 전달 되는 문자열은 로그인을 시도하는 회원의 id
    // 로그인 html에서 입력하는 id input 태그의 name 속성 값을
    // 반드시 username으로 설정해야 id 값이 넘어 옴
    // 비밀번호 input 태그의 name 속성 값을 password로 지정하면
    // 해당 input 태그의 데이터를 비밀번호로 간주.

    // 로그인을 실행하는 url이 호출 되면 아래의 메소드가 자동으로 실행
    // 로그인 하려는 사람의 로그인 기능 수행에 필요한 정보를 조회해서 시큐리티에게 전달하는 코드를 작성
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인 하려는 회원의 정보 조회
        MemberVO member = boardService.login(username);

        // 조회 한 정보를 UserDetails 타입으로 변환
        UserDetails userInfo = User.builder()
                                    .username(member.getMemberId())
                                    // 비밀번호를 시큐리티에게 전달하면
                                    // 기본적으로 시큐리티는 비밀번호가 암호화 되어 있다고 판단
                                    // 암호화 되지 않은 비밀번호는
                                    // {noop}으로 암호화를 하지 않았음을 알려줘야 함
                                    .password(member.getMemberPw())
                                    .roles(member.getMemberRoll())
                                    .build();

        return userInfo;
    }
}
