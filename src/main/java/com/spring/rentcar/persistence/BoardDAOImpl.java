package com.spring.rentcar.persistence;


import com.spring.rentcar.commons.SearchCriteria;
import com.spring.rentcar.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private static final String NAMESPACE ="com.spring.rentcar.mappers.BoardMapper";

    @Inject
    private SqlSession sqlSession;

    @Override
    public void insertBoard(BoardVO boardVO) throws Exception {

        sqlSession.insert(NAMESPACE+".insertBoard", boardVO);
    }

    @Override
    public BoardVO readBoard(int bno) throws Exception {

        return sqlSession.selectOne(NAMESPACE+".readBoard", bno);
    }

    @Override
    public void updateBoard(BoardVO boardVO) throws Exception {

        sqlSession.update(NAMESPACE+".updateBoard", boardVO);
    }

    @Override
    public void deleteBoard(int bno) throws Exception {

        sqlSession.delete(NAMESPACE+".deleteBoard", bno);
    }

    @Override
    public List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception {

        return sqlSession.selectList(NAMESPACE+".listSearch", searchCriteria);
    }

    @Override
    public int countSearchBoard(SearchCriteria searchCriteria) throws Exception {

        return sqlSession.selectOne(NAMESPACE+".countSearchBoard", searchCriteria);
    }

    @Override
    public void viewCntBoard(int bno) throws Exception {
        sqlSession.update(NAMESPACE+".viewCntBoard", bno);
    }

    @Override
    public void updateReplyCnt(int bno, int amount) throws Exception {

        Map<String, Object> map = new HashMap<>();

        map.put("bno", bno);
        map.put("amount", amount);

        sqlSession.update(NAMESPACE+".updateReplyCnt", map);
    }
}
