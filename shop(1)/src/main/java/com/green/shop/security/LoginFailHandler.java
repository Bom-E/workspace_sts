package com.green.shop.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

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

        String errorMsg = "";
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMsg = "아이디 또는 비밀번호가 틀립니다.";
        }
        else if (exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 사용자 ID 입니다.";
            // 아이디 혹은 비밀번호 중 무엇이 틀린지 알려 준다면 보안의 취약점이 생겨서
            // 시큐리티 내부에 숨겨져 있다
            // 따라서 두번째는 실행되지 않는다.
        }
        else {
            errorMsg = "알 수 없는 이유로 로그인이 실패했습니다. \n 관리자에게 문의 하세요.";
        }

        // 한글 인코딩 변환
        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");

        // 이동 할 페이지를 설정
        response.sendRedirect("/member/loginForm?errorMsg=" + errorMsg);
    }
}
