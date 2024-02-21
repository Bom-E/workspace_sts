package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {

    List<ClassVO> selectClassList();

    List<StuVO> selectStu(StuVO stuVO);

    //학생 상세 정보 조회
    StuVO selectStuInfo(int stuNum);

    //점수 정보 조회
    ScoreVO selectScoreInfo(int stuNum);

    void insertScore(ScoreVO scoreVO);
}
