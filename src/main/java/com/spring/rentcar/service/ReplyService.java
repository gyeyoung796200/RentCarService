package com.spring.rentcar.service;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    //게시판번호로 댓글 조회
    public List<ReplyVO> list(int bno) throws Exception;

    //댓글 등록
    public void insert(ReplyVO replyVO) throws Exception;

    //댓글 수정
    public void update(ReplyVO replyVO) throws Exception;

    //댓글 삭제
    public void delete(int replyNo) throws  Exception;

    //댓글 페이징
    public List<ReplyVO> listPaging(int bno, Criteria criteria) throws Exception;

    //댓글 개수
    public int countReply(int bno) throws Exception;
}
