package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    int insertReply(ReplyVO replyVO);

    void deleteReply(int replyNum);

    int deleteAll(int boardNum);

    int updateReply(ReplyVO replyVO);

    List<ReplyVO> selectReply(int bordNum);
}
