package com.green.BasicBoard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링 시큐리티 인증, 인가에 대한 프로세스를 정의

// 이 클래스가 시큐리티 설정 파일임을 인지시켜주는 역할
@EnableWebSecurity
// 객체 생성 어노테이션 (@Controller, @Service)
@Configuration
public class SecurityConfig {

    // Bean : 객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 객체로 생성
    // 암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security)throws Exception {
                // 아래는 csrf 공격에 대한 방어를 해지 하는 것
        security.csrf(AbstractHttpConfigurer::disable)
                // .authorizeHttpRequests 메소드 안에 인증, 인가 관리
                .authorizeHttpRequests(
                        c -> {
                            c.requestMatchers(
                                 new AntPathRequestMatcher("/"),
                                 new AntPathRequestMatcher("/loginForm"),
                                 new AntPathRequestMatcher("/login"),
                                 new AntPathRequestMatcher("/joinForm"),
                                 new AntPathRequestMatcher("/join"),
                                 new AntPathRequestMatcher("/sample")
                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/admin")
                            ).hasRole("ADMIN")
                            .requestMatchers(
                                    new AntPathRequestMatcher("/manager")
                            ).hasRole("MANAGER")
                            .requestMatchers(
                                    new AntPathRequestMatcher("/boardWriteForm")
                            ).hasAnyRole("USER", "MANAGER")
                            .anyRequest().authenticated();
                        }
                )
                // 1. 로그인 form을 활용
                // 2. 로그인 설정 내용 포함
                .formLogin(
                    formLogin -> {
                        // 로그인 페이지 url 설정
                        // 이 설정을 하면 인증이 안 될시 거부 페이지가 아닌 로그인 페이지로 보낸다
                        formLogin.loginPage("/loginForm")
                                // 기본적으로 유저의 id는 username으로, 유저의 pw는 password로
                                // input 태그의 name 속성 값을 지정해야 하지만
                                // 아래의 메소드를 활용하면 내가 지정한 속성값이 지정 됨.
                                // 로그인 시 전달 되는 id 및 password의 name 속성 값을 지정
                                .usernameParameter("memberId")
                                .passwordParameter("memberPw")
                                // 로그인 기능이 실행 되는 url
                                .loginProcessingUrl("/login")
                                // 로그인 성공 시 이동 할 url
                                // 매개변수 두 개 들어갈 수 있음
                                // true : 로그인 성공 시 항상 첫번째 매개변수의 url로 이동
                                // false, 혹은 두 번째 매개변수 설정 x : 이전 페이지로 이동 이전 페이지가 없다면 설정한 페이지로
                                .defaultSuccessUrl("/")
                                .failureUrl("/loginForm");
                        }
                )
                .logout(
                    logout -> {
                        // 해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                        logout.logoutUrl("/logout")
                                // 로그아웃 성공 시 이동 할 url
                                .logoutSuccessUrl("/")
                                // 로그아웃 성공 시 세션 데이터 삭제
                                .invalidateHttpSession(true);
                        }
                )
                // 예외 발생 시 처리해야 하는 내용 작성
                .exceptionHandling(
                        ex -> {
                            ex.accessDeniedPage("/deny");
                        }
                );

        return security.build();
    }

}
