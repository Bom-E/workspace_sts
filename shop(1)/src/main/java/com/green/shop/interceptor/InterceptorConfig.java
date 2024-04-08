package com.green.shop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 생성한 Interceptor 클래스의 적용 시점 정의
// WebMvcConfigurer 인터페이스를 구현 후
// addInterceptors() 메소드를 오버라이딩
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // 꼭 만들어야 하는 것
    // Interceptor가 실행되는 url 지정
    // url에 접근 할 때 마다 실행.
    // addInterceptors() : 이 메소드 안에 실행시킬 Interceptor의 객체를 전달
    // addPathPatterns() : Interceptor 기능이 실행 되는 url을 지정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getPrintInterceptor())
                .order(2)
                // .addPathPatterns("/**/**")
                .addPathPatterns("/item/**")
                // 비동기 전체 제외, 비동기 경로에 Fetch를 넣으면 됨.
                .excludePathPatterns("/item/**Fetch");

        // 중요! Interceptor는 비동기 통신 메소드에는 사용 불가!
        // 그래서 비동기로 실행 되는 메소드는 반드시 Interceptor 실행에서 재외를 시커 줘야 함
        // 비동기 통신의 작동 원리 때문.

        registry.addInterceptor(getDbInterceptor())
                .order(1)
                .addPathPatterns("/item/test1")
                .addPathPatterns("/item/test3");
    }

    // @Bean : 메소드의 리턴 데이터를 객체로 생성
    @Bean
    // 필요에 의해 만든 것
    public PrintInterceptor getPrintInterceptor(){
        return new PrintInterceptor();
    }

    @Bean
    public DBInterceptor getDbInterceptor(){
        return new DBInterceptor();
    }
}
