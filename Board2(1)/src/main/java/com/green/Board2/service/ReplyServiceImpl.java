package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    SqlSessionTemplate sqlSession;

    @Override
    public int insertReply(ReplyVO replyVO) {
        return sqlSession.update("replyMapper.insertReply", replyVO);
    }

    @Override
    public void deleteReply(int replyNum) {
        sqlSession.delete("replyMapper.deleteReply", replyNum);
    }

    @Override
    public int deleteAll(int boardNum) {
        return sqlSession.delete("replyMapper.deleteAll", boardNum);
    }

    @Override
    public int updateReply(ReplyVO replyVO) {
        return sqlSession.update("replyMapper.updateReply", replyVO);
    }

    @Override
    public List<ReplyVO> selectReply(int bordNum) {
        return sqlSession.selectList("replyMapper.selectReply", bordNum);
    }
}
