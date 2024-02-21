package com.green.Board2.controller;

import com.green.Board2.service.ReplyService;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    //댓글 등록
    @PostMapping("/regReply")
    public String regReply(ReplyVO replyVO, HttpSession session){
        //저장 된 로그인 정보 가져오기
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        //작성자 명을 로그인 정보에 저장 된 아이디로 가져오기
        replyVO.setWriter(loginInfo.getMemberId());
        //댓글 등록 기능 실행
        replyService.insertReply(replyVO);
        return "redirect:/board/detail?boardNum=" + replyVO.getBoardNum();
    }
}
