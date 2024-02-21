package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
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
    @RequestMapping("/list")
    //커맨드 객체 : 괄호의 객체, model이 없어도 데이터를 자동으로 넘겨줌. 객체명은 뭘 해도 상관 없지만,
    //자동으로 넘어 갈 때, 클래스명에서 앞 글자만 소문자로 바꾼 객체명으로 넘어감.
    public String boardList(SearchVO searchVO, Model model){
        //전체 데이터 수
        int totalDtaCnt = boardService.selectBoardCnt(searchVO);
        searchVO.setTotalDataCnt(totalDtaCnt);

        //페이지 정보 세팅
        searchVO.setPageInfo();

        model.addAttribute("selectAll", boardService.selectAll(searchVO));
        return "list";
    }

    //게시글 작성 페이지
    @GetMapping("/writePost")
    public String writePost(){
        return "go_To_Write";
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
    public String selectOne(@RequestParam(name = "boardNum")int boardNum, ReplyVO replyVO, HttpSession session, Model model){
        model.addAttribute("detail", boardService.selectOne(boardNum));
        boardService.updateReadCnt(boardNum);
        replyVO.setWriter(session.getId());
        model.addAttribute("selectReply", replyService.selectReply(boardNum));
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
    public String deletePost(BoardVO boardVO){
        replyService.deleteAll(boardVO.getBoardNum());
        boardService.deletePost(boardVO);
        return "redirect:/board/list";
    }
}
