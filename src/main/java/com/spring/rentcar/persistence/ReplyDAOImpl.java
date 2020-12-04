package com.spring.rentcar.persistence;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.domain.ReplyVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

    private static final String NAMESPACE = "com.spring.rentcar.mappers.ReplyMapper";

    @Inject
    private SqlSession sqlSession;

    @Override
    public List<ReplyVO> list(int bno) throws Exception {

        return sqlSession.selectList(NAMESPACE+".list", bno);
    }

    @Override
    public void insert(ReplyVO replyVO) throws Exception {

        sqlSession.insert(NAMESPACE+".insert", replyVO);
    }

    @Override
    public void update(ReplyVO replyVO) throws Exception {

        sqlSession.update(NAMESPACE+".update", replyVO);
    }

    @Override
    public void delete(int replyNo) throws Exception {

        sqlSession.delete(NAMESPACE+".delete", replyNo);
    }

    @Override
    public List<ReplyVO> listPaging(int bno, Criteria criteria) throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("bno", bno);
        map.put("criteria", criteria);

        return sqlSession.selectList(NAMESPACE+".listPaging", map);

    }

    @Override
    public int countReply(int bno) throws Exception {

        return sqlSession.selectOne(NAMESPACE+".countReply", bno);
    }

    @Override
    public int getBno(int replyNo) throws Exception {

        return sqlSession.selectOne(NAMESPACE+".getBno", replyNo);
    }
}
