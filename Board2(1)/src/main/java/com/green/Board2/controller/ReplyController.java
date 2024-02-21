package com.green.Board2.controller;

import com.green.Board2.service.MemberServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    @PostMapping("/insert")
    public String insertReply(ReplyVO replyVO, HttpSession session, Model model){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        replyVO.setWriter(loginInfo.getMemberId());
        model.addAttribute("insertReply", replyService.insertReply(replyVO));
        return "redirect:/board/detail?boardNum=" + replyVO.getBoardNum();
    }

    @GetMapping("/deleteReply")
    public String deleteReply(@RequestParam(name = "replyNum")int replyNum, BoardVO boardVO){
        replyService.deleteReply(replyNum);
        return "redirect:/board/detail?boardNum=" + boardVO.getBoardNum();
    }

    @GetMapping("/goUpdateReply")
    public String goUpdateReply(ReplyVO replyVO, Model model){
        model.addAttribute("reply", replyVO);
        System.out.println(replyVO);
        return "popup";
    }

    @PostMapping("/updateReply")
    public String updateReply(ReplyVO replyVO){
        System.out.println(replyVO);
        replyService.updateReply(replyVO);
        return "close_popup";
    }
}
