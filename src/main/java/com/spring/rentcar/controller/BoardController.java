package com.spring.rentcar.controller;

import com.mysql.jdbc.StringUtils;
import com.spring.rentcar.commons.PageMaker;
import com.spring.rentcar.commons.SearchCriteria;
import com.spring.rentcar.domain.BoardVO;
import com.spring.rentcar.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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


    @GetMapping("/insert")
    public String BoardInsertGet() throws Exception{

        return "board/insert";
    }

    @PostMapping("/insert")
    public String BoardInsertPost(BoardVO boardVO, RedirectAttributes rttr) throws Exception{

        boardService.insertBoard(boardVO);
        rttr.addFlashAttribute("msg", "regSuccess");

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String BoardReadGet(@RequestParam("bno") int bno, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model, HttpServletRequest req, HttpServletResponse resp)throws Exception{

        logger.info("read get");

        //저장된 쿠기 불러오기
        Cookie[] cookies = req.getCookies();

        Map map = new HashMap();

        if(req.getCookies() != null){
            for(int i = 0; i <cookies.length; i++){
                Cookie obj = cookies[i];
                map.put(obj.getName(), obj.getValue());
            }
        }
        //저장된 쿠키에서 viewCnt불러오기
        String viewCnt = (String)map.get("viewCnt");
        //저장될 새로운 쿠키값 생성
        String newViewCnt = "|" + bno;

        //저장되된 쿠키에 새로운 쿠키값이 존재하는지 검사
        if(StringUtils.indexOfIgnoreCase(viewCnt, newViewCnt) == -1){

            //없을 경우 쿠키 생성
            Cookie cookie = new Cookie("viewCnt", viewCnt + newViewCnt);

            resp.addCookie(cookie);
            boardService.viewCntBoard(bno);
        }



        model.addAttribute("board", boardService.readBoard(bno));

        return "board/read";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String BoardModifyGet(@RequestParam("bno")int bno, @ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model)throws Exception{

        logger.info("modify get");

        model.addAttribute("board",boardService.readBoard(bno));

        return "board/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String BoardModifyPost(BoardVO boardVO, SearchCriteria searchCriteria, RedirectAttributes rttr)throws Exception{

        logger.info("modify post");

        boardService.updateBoard(boardVO);

        rttr.addAttribute("page", searchCriteria.getPage());
        rttr.addAttribute("perPageNum", searchCriteria.getPerPageNum());
        rttr.addAttribute("searchType", searchCriteria.getSearchType());
        rttr.addAttribute("keyWord", searchCriteria.getKeyWord());
        rttr.addFlashAttribute("msg", "modSuccess");

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String BoardDeletePost(@RequestParam("bno") int bno, SearchCriteria searchCriteria, RedirectAttributes rttr)throws Exception{

        logger.info("delete post");
        boardService.deleteBoard(bno);

        rttr.addAttribute("page",searchCriteria.getPage());
        rttr.addAttribute("perPageNum", searchCriteria.getPerPageNum());
        rttr.addAttribute("searchType", searchCriteria.getSearchType());
        rttr.addAttribute("keyWord", searchCriteria.getKeyWord());
        rttr.addFlashAttribute("msg", "delSuccess");

        return "redirect:/board/list";
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listGet(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) throws Exception{


        logger.info("listPaging Get");

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(searchCriteria);
        pageMaker.setTotalCount(boardService.countSearchBoard(searchCriteria));

        model.addAttribute("boardList", boardService.listSearch(searchCriteria));
        model.addAttribute("pageMaker", pageMaker);

        return "board/list";
    }

}
