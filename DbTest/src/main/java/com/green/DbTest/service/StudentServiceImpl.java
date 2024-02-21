package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StudentServiceImpl implements StudentService{
    //Mybatis(db 프레임워크)에서 제공하는 데이터베이스 쿼리 기능이
    //정의되어있는 객체
    @Autowired //밑의 객체의 생성자 생성
    private SqlSessionTemplate sqlSession;

    //디비작업 : 조회, 삽입, 삭제, 수정
    // 삽입 : sqlSession.insert("실행할 쿼리 id", [퀴리에서 빈 값을 채울 데이터])
    // 삭제 : sqlSession.delete("실행할 쿼리 id", [퀴리에서 빈 값을 채울 데이터])
    // 수정 : sqlSession.update("실행할 쿼리 id", [퀴리에서 빈 값을 채울 데이터])
    // 조회 : sqlSession.selectOne("실행할 쿼리 id", [퀴리에서 빈 값을 채울 데이터])
    //       sqlSession.selectList("실행할 쿼리 id", [퀴리에서 빈 값을 채울 데이터])
    // selectOne : 조회 결과 데이터가 0행 혹은 1행일 경우, PRIMARY KEY가 들어가는 조건문 경우
    // selectList : 조회 할 때 마다 조회 되는 행의 갯수가 매번 다른 경우, PRIMARY KEY가 없는 조건문

    @Override
    //학생 한 명을 저장하는 기능
    public void insertStudent() {
        sqlSession.insert("insertStudent");
        //쿼리 실행을 위한 메소드
    }

    @Override
    //학생 한 명을 삭제하는 기능
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) {
        sqlSession.delete("delete", stuNo);
        //delete 라는 아이디를 가진 쿼리 실행 하면서
        // 빈 값을 stuNo가 들어온다.
    }

    @Override
    public String selectName() {
        String name = sqlSession.selectOne("selectName");
        return name;
    }

    @Override
    public int selectScore() {
        int score = sqlSession.selectOne("selectScore");
        return score;
    }

    public StudentVO selectStu(){
        StudentVO stu = sqlSession.selectOne("selectStu");
        return stu;
    }

    @Override
    public List<StudentVO> selectStuLIST() {
        List<StudentVO> list = sqlSession.selectList("selectStuList");
        return list;
    }

}
