package com.spring.rentcar.controller;


import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.commons.PageMaker;
import com.spring.rentcar.domain.ReplyVO;
import com.spring.rentcar.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies")
public class ReplyController {

    @Inject
    private ReplyService replyService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody ReplyVO replyVO) {

        ResponseEntity<String> entity = null;

        try {
            replyService.insert(replyVO);
            entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno")int bno){

        ResponseEntity<List<ReplyVO>> entity = null;
        try{
            List<ReplyVO> list = replyService.list(bno);
            entity = new ResponseEntity<>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @RequestMapping(value = "/{replyNo}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@PathVariable("replyNo")int replyNo, @RequestBody ReplyVO replyVO){

        ResponseEntity<String> entity = null;
        try{
            replyVO.setReplyNo(replyNo);
            replyService.update(replyVO);
            entity = new ResponseEntity<>("modSuccess", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @DeleteMapping("/{replyNo}")
    public ResponseEntity<String> delete(@PathVariable("replyNo")int replyNo){
        ResponseEntity<String> entity = null;

        try{
            replyService.delete(replyNo);
            entity = new ResponseEntity<>("delSuccess", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @GetMapping("/{bno}/{page}")
    public ResponseEntity<Map<String, Object>> listPaging(@PathVariable("bno")int bno, @PathVariable("page")int page){

        ResponseEntity<Map<String, Object>> entity = null;


        try{
            Criteria cri = new Criteria(); // 넘겨받은 페이지번호와 한페이지당 보여지는 댓글의 개수를 설정하기위해 생성
            cri.setPage(page);          //넘겨받은 페이지를 cri에 저장한다

            PageMaker pageMaker = new PageMaker();  //페이징 계산을 위해 PageMaker 생성
            pageMaker.setCriteria(cri); //Criteria에 저장한 페이지정보를 가지고있는 cri를 PageMaker에 저장한다


            List<ReplyVO> repliesList = replyService.listPaging(bno, cri); //댓글을 넘겨받은 게시글번호, 현재페이지, 한페이지에 보여지는 글개수를 보내서 list를 만든다

            int total = replyService.countReply(bno); //pageMaker는 total 댓글이 정해져야 계산을 해서 total변수를 만든다

            pageMaker.setTotalCount(total);

            Map<String, Object> map = new HashMap<>();

            map.put("pageMaker", pageMaker);
            map.put("replies", repliesList);

            entity = new ResponseEntity<>(map, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
