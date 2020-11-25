package com.spring.rentcar.controller;

import com.spring.rentcar.domain.BoardVO;
import com.spring.rentcar.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

@Controller
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Inject
    private BoardService boardService;

//    @RequestMapping("/list")
//    public ModelAndView BoardListGet() throws Exception{
//
//        ModelAndView mav = new ModelAndView();
//
//        mav.addObject("boardList",boardService.listBoard());
//        mav.addObject("mainData", "board/list.jsp");
//        mav.setViewName("index");
//
//        return mav;
//    }

    @GetMapping("/list2")
    public String BoardListGet(Model model) throws Exception{


        model.addAttribute("boardList",boardService.listBoard());

        return "board/list2";
    }

    @GetMapping("/insert")
    public String BoardInsertGet() throws Exception{

        return "board/insertForm";
    }

    @PostMapping("/insert")
    public String BoardInsertPost(BoardVO boardVO, RedirectAttributes rttr) throws Exception{

        boardService.insertBoard(boardVO);
        rttr.addFlashAttribute("msg", "regSuccess");

        return "redirect:/board/list2";
    }

    @GetMapping("/read")
    public String BoardReadGet(@RequestParam("bno")int bno, Model model)throws Exception{

        model.addAttribute("board", boardService.readBoard(bno));

        return "board/read";
    }

    @GetMapping("/modify")
    public String BoardModifyGet(@RequestParam("bno")int bno, Model model)throws Exception{

        model.addAttribute("board",boardService.readBoard(bno));

        return "board/modify";
    }

    @PostMapping("/modify")
    public String BoardModifyPost(BoardVO boardVO, RedirectAttributes rttr)throws Exception{


        boardService.updateBoard(boardVO);
        rttr.addFlashAttribute("msg", "modSuccess");

        return "redirect:/board/list2";
    }

    @PostMapping("/delete")
    public String BoardDeletePost(@RequestParam("bno")int bno, RedirectAttributes rttr)throws Exception{

        boardService.deleteBoard(bno);
        rttr.addFlashAttribute("msg", "delSuccess");

        return "redirect:/board/list2";
    }


}
