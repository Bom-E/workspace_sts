package com.green.shop.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 로그인 실패 시 실행하는 클래스
// AuthenticationFailureHandler 라는 인터페이스를 구현한 클래스를 생성하면
// onAuthenticationFailure 라는 메소드를 정의해야 한다.
// 이 메소드가 로그인 실패 시 자동으로 호출된다.

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

    // 로그인 실패 시 자동으로 호출 되는 메소드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("잠온다");

        // 이동 할 페이지를 설정
        response.sendRedirect("/member/loginForm?errorMsg=fail");
    }
}
