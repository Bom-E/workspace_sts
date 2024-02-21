package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyService;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    //메인 페이지
    @RequestMapping("/list") //@RequestMapping > Get, Post 다 됨
    public String boardList(SearchVO searchVO, Model model){
        model.addAttribute("selectAll", boardService.selectAll(searchVO));
        return "list";
    }

    @GetMapping("/goToSearch")
    public String goToSearch(){

        return "redirect:/board/list";
    }

    //게시글 작성
    @PostMapping("/writeIt")
    public String writeThePost(BoardVO boardVO, HttpSession session){
        //세션에 저장 된 로그인 한 유저의 아이디를 조회
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.writePost(boardVO);
        return "redirect:/board/list";
    }

    //게시글 상세 페이지
    @GetMapping("/detail")
    public String selectOne(@RequestParam(name = "boardNum")int boardNum, Model model){
        boardService.updateReadCnt(boardNum);

        model.addAttribute("detail", boardService.selectOne(boardNum));

        model.addAttribute("selectReplyList", replyService.selectReplyList(boardNum));

        return "detail_post";
    }

    //게시글 수정 페이지
    @GetMapping("/goToUpdate")
    public String goToUpdate(BoardVO boardVO, Model model){
        model.addAttribute("update", boardService.selectOne(boardVO.getBoardNum()));
        return "update_post";
    }

    //게시글 수정
    @PostMapping("/updatePost")
    public String updatePost(BoardVO boardVO){
        boardService.updatePost(boardVO);
        return "redirect:/board/detail?boardNum=" + boardVO.getBoardNum();
    }

    //게시글 삭제
    @GetMapping("/deletePost")
    public String deletePost(@RequestParam(name = "boardNum") int boardNum){
        boardService.deletePost(boardNum);
        return "redirect:/board/list";
    }
}
