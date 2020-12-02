package com.spring.rentcar.service;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.domain.ReplyVO;
import com.spring.rentcar.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {


    @Inject
    private ReplyDAO replyDAO;

    @Override
    public List<ReplyVO> list(int bno) throws Exception {

        return replyDAO.list(bno);
    }

    @Override
    public void insert(ReplyVO replyVO) throws Exception {

        replyDAO.insert(replyVO);
    }

    @Override
    public void update(ReplyVO replyVO) throws Exception {

        replyDAO.update(replyVO);
    }

    @Override
    public void delete(int replyNo) throws Exception {

        replyDAO.delete(replyNo);
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
