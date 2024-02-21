package com.green.DbTest.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//STUDENT 테이블과 매칭되는 클래스
@Getter
@Setter
@ToString
public class StudentVO {
    //변수명은 자바식으로 바꿔야 함.
    private int stuNo;
    private String stuName;
    private int score;
    private String addr;
}
