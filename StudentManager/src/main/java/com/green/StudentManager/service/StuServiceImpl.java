package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public int insertStu(StuVO stuVO) {
        return sqlSession.insert("insertStu", stuVO);
    }

    @Override
    public List<StuVO> selectStuList() {
        //빈 값 채울 게 없으니까 두번째는 없음
        return sqlSession.selectList("selectStuList");
    }

    @Override
    //WHERE이 있어서 조건이 있으니까 매개변수에 빈 값이 필요함.
    public StuVO selectStu(int stuNo) {
        return sqlSession.selectOne("selectStu", stuNo);
    }

    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }
}
