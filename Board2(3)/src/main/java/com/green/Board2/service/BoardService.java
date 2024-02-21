package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {
    //빈 값 채울 매개변수 여러개 = VO
    //                 하나 = boardNum

    List<BoardVO> selectAll(SearchVO searchVO);

    int writePost(BoardVO boardVO);

    BoardVO selectOne(int boardNum);

    void updatePost(BoardVO boardVO);

    int deletePost(int boardNum);

    int updateReadCnt(int boardNum);

}
