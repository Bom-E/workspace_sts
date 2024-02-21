package com.green.BasicBoard.controller;

import com.green.BasicBoard.service.BasicBoardImpl;
import com.green.BasicBoard.vo.BasicBoardVO;
import jakarta.annotation.Resource;
import org.attoparser.ParsingProcessingInstructionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BsicBoardController {
    @Resource(name = "basicBoard")
    private BasicBoardImpl basicBoard;

    // 게시판 메인
    @GetMapping("/")
    public String main(Model model){
        //게시판 목록 조회
        List<BasicBoardVO> list = basicBoard.selectBoardList();
        //조회한 목록을 html로 전달
        model.addAttribute("boardList", list);
        //데이터 조회
        //System.out.println(list.size());
        return "board_list";
    }

    //글 쓰기 페이지로 이동
    @GetMapping("/clickWriteButton")
    public String goinsert(){
        //목록 데이터 조회 후 HTML 전달

        return "board_insert";
    }

    @PostMapping("/insertBoard")
    public String insert (BasicBoardVO basicBoardVO){
        //게시물 작성
        basicBoard.insertPost(basicBoardVO);

        return "redirect:/";
    }

    @GetMapping("/goDetail")
    public String goDetail(@RequestParam(name = "boardNum") int boardNum, Model model){
        BasicBoardVO detail = basicBoard.boardDetail(boardNum);
        basicBoard.plusCnt(boardNum);
        model.addAttribute("boardDetail", detail);
        return "post_Detail";
    }

    @GetMapping("/deletePost")
    public String deletePost(BasicBoardVO basicBoardVO){
        basicBoard.deletePost(basicBoardVO);
        return "redirect:/";
    }

    @GetMapping("/goUpdate")
    public String goUpdate(@RequestParam(name = "boardNum") int boardNum, Model model){
        //수정하고자 하는 게시글의 데이터를 조회 + html 전달
        BasicBoardVO reserve = basicBoard.boardDetail(boardNum);
        model.addAttribute("reserve", reserve);
        return "update_Post";
    }

    //게시글 수정
    @PostMapping("/updatePost")
    public String updatePost(BasicBoardVO basicBoardVO){
        basicBoard.updatePost(basicBoardVO);
        return "redirect:/goDetail?boardNum=" + basicBoardVO.getBoardNum();
    }
}

