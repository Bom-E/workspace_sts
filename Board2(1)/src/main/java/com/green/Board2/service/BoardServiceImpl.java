package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<BoardVO> selectAll(SearchVO searchVO) {
        return sqlSession.selectList("boardMapper.selectAll", searchVO);
    }

    @Override
    public int writePost(BoardVO boardVO) {
        return sqlSession.insert("boardMapper.writePost", boardVO);
    }

    @Override
    public BoardVO selectOne(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectOne", boardNum);
    }

    @Override
    public void updatePost(BoardVO boardVO) {
        sqlSession.update("boardMapper.updatePost", boardVO);
    }

    @Override
    public int deletePost(BoardVO boardVO) {
        return sqlSession.delete("boardMapper.deletePost", boardVO);
    }

    @Override
    public int updateReadCnt(int boardNum) {
        return sqlSession.update("boardMapper.updateReadCnt", boardNum);
    }

    @Override
    public int selectBoardCnt(SearchVO searchVO) {
        return sqlSession.selectOne("boardMapper.selectBoardCnt", searchVO);
    }
}
