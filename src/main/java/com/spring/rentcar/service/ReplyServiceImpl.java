package com.spring.rentcar.service;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.domain.ReplyVO;
import com.spring.rentcar.persistence.BoardDAO;
import com.spring.rentcar.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {


    private final ReplyDAO replyDAO;

    private final BoardDAO boardDAO;

    @Inject
    public ReplyServiceImpl(ReplyDAO replyDAO, BoardDAO boardDAO){
        this.replyDAO = replyDAO;
        this.boardDAO = boardDAO;
    }

    @Override
    public List<ReplyVO> list(int bno) throws Exception {

        return replyDAO.list(bno);
    }

    @Transactional
    @Override
    public void insert(ReplyVO replyVO) throws Exception {
        replyDAO.insert(replyVO);
        boardDAO.updateReplyCnt(replyVO.getBno(), 1);
    }

    @Override
    public void update(ReplyVO replyVO) throws Exception {

        replyDAO.update(replyVO);
    }

    @Transactional
    @Override
    public void delete(int replyNo) throws Exception {

        int bno = replyDAO.getBno(replyNo);
        replyDAO.delete(replyNo);
        boardDAO.updateReplyCnt(bno, -1);
    }

    @Override
    public List<ReplyVO> listPaging(int bno, Criteria criteria) throws Exception {

        return replyDAO.listPaging(bno, criteria);
    }

    @Override
    public int countReply(int bno) throws Exception {
        return replyDAO.countReply(bno);
    }
}