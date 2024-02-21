package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {

    //SELECT 리턴 : 매번 바뀜
    //INSERT, DELETE, UPDATE : void, int(정석)
    //바로 조회가 안 되는 거랑 관련이 있는데, 콘솔에서 조회가 아닌 영향 받은 행으로
    //확인 할 수 있어서 정수인 int로 받아야 함.

    //리턴 타입 : 쿼리 실행 결과를 받아옴.
    //INSERT, UPDATE, DELETE 쿼리 실행 결과가
    //몇개의 행이 삽입, 삭제, 수정 되었는지를 보여줌.
    //그래서 위 세 개의 쿼리 결과 리턴 티입은 무조건 int, void
    //SELECT는 어떤 쿼리인지에 따라 리턴타입이 달라짐.
    //조회 결과 데이터가 0행 혹은 1행 : VO 클래스
    //조회 결과 데이터가 그 이상 : List<VO 클래스>

    //SELECT는 빈 값 채울 게 없어서 매개변수가 필요없다.
    //하지만 조건문이 들어가면 빈 값을 채워야 함.
    //매개변수 : 쿼리 실행 시 빈 값을 채우는 역할
    //빈 값을 채울 데이터가 0개, 1개, 여러개 구분.
    //빈 값 0 개 : 매개변수 필요 없음.
    //빈 값 1 개 :
    //  1) 빈 값의 자료형이 숫자인 경우 : 매개변수로 int 자료형 하나
    //  2) 빈 값의 자료형이 문자열인 경우 : 매개변수로 String 자료형 하나
    //빈 값이 여러개인 경우는 VO객체

    //학생 등록
    int insertStu(StuVO stuVO);

    //StuVO test(StuVO stuVO);
    //primary key는 무조건 한 행만 나옴!!

    List<StuVO> selectStuList();

    StuVO selectStu(int stuNo);
    //WHERE이 있어서 조건이 있으니까 빈 값이 필요함.

    int deleteStu(int stuNo);
}
