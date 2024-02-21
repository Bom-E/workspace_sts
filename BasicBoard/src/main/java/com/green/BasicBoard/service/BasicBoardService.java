package com.green.BasicBoard.service;

import com.green.BasicBoard.vo.BasicBoardVO;

import java.util.List;

public interface BasicBoardService {

    //게시글 목록 조회
    List<BasicBoardVO> selectBoardList();

    //게시글 등록
    void insertPost (BasicBoardVO basicBoardVO);

    BasicBoardVO boardDetail(int boardNum);

    int deletePost(BasicBoardVO basicBoardVO);

    void updatePost(BasicBoardVO basicBoardVO);

    int plusCnt(int boardNum);
}
