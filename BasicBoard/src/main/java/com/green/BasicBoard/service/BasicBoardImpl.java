package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.BasicBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basicBoard") //BasicBoardImpl의 객체, Controller의 Resource와 연결됨/ Service: IOC, Resource: DI
public class BasicBoardImpl implements BasicBoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<BasicBoardVO> selectBoardList() {
        return sqlSession.selectList("boardMapper.selectBoardList");
    }

    @Override
    public void insertPost(BasicBoardVO basicBoardVO) {
        sqlSession.insert("boardMapper.insertPost", basicBoardVO);
    }

    @Override
    public BasicBoardVO boardDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.boardDetail", boardNum);
    }

    @Override
    public int deletePost(BasicBoardVO basicBoardVO) {
        return sqlSession.delete("deletePost" ,basicBoardVO);
    }

    @Override
    public void updatePost(BasicBoardVO basicBoardVO) {
        sqlSession.update("updatePost", basicBoardVO);
    }

    @Override
    public int plusCnt(int boardNum) {
        return sqlSession.update("plusCnt", boardNum);
    }
}
