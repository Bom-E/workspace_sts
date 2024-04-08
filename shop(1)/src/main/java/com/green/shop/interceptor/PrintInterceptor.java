package com.green.shop.interceptor;

import com.green.shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// Interceptor 클래스
// HandlerInterceptor 인터페이스를 구현한 클래스는 Interceptor 클래스로 인식
// 해당 클래스에는 반복 되는 기능을 정의
// 반복 되는 기능의 호출 시점에 따라서
// HandlerInterceptor 인터페이스의 메소드를 오버라이딩
// 오버라이딩 할 수 있는 메소드
// preHandle() : controller 안의 메소드 실행 직전에 호출
// postHandle() : controller 안의 메소드 실행 후에 호출
// afterCompletion() : controller 안의 메소드 실행 후 html까지 다 실행 되면 호출
public class PrintInterceptor implements HandlerInterceptor {

    // 리턴 타입이 boolean인 이유 : 데이터의 확인 (대빵 컨트롤러(디스패쳐)가 부하 컨트롤러로 보내기 전에 거치는 검문소)
    // true : 필수 데이터가 있으면 true, 원래 가야 할 컨트롤러로 이동
    // false : 필수 데이터가 없으면 false, 다른 컨트롤러로 이동
    // 주로 권한 체크용으로 사용 되었음
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("PrintInterceptor : preHandle() 메소드 실행~~~");

        return true;
    }

    // 리턴 타입이 void인 이유 : 이미 컨트롤러를 떠나서. (부하 컨트롤러에서 html로 갈 때 거치는 검문소)
    // ModelAndView : 컨트롤러에서 넘어가는 데이터를 확인해줌. 만약 필요한 정보가 안 담겨 있다면 알아서 담아줌
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("PrintInterceptor : postHandle() 메소드 실행~~~");

        // 컨트롤러에서 전달 한 데이터 확인
        Map<String, Object> modelData = modelAndView.getModel();

        String name = modelData.get("name").toString();
        MemberVO member = (MemberVO) modelData.get("member");

        // model.addAttribute랑 같은 기능
        modelAndView.addObject("addr", "울산");
    }
}
