package com.green.BasicBoard.vo;

import lombok.*;

// Data : 게터 세터 투스트링 한번에 생성 + 기본 생성자
// NoArgsConstructor : 매개변수가 없는 기본 생성자 생성
// AllArgsConstructor : 매개변수로 모든 변수를 갖는 생성자 생성
// RequiredArgsConstructor : 필수로 받아야 하는 매개변수를 가지는 생성자를 생성
// Builder : 여러 조건의 생성자를 자동을 만들어주는 어노테이션

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberName;
    private String memberPw;
    private String memberRoll;
}

class BuilderTest{

    public void test(){
        // id를 java라는 초기 값으로 갖는 객체 생성
        MemberVO v1 = MemberVO.builder()
                              .memberId("java")
                              .build();

        MemberVO v2 = MemberVO.builder()
                              .memberId("java")
                              .memberName("hong")
                              .build();

        MemberVO v3 = MemberVO.builder()
                              .memberName("hong")
                              .memberPw("1111")
                              .memberRoll("USER")
                              .build();

        MemberVO v4 = MemberVO.builder().build();
    }

}