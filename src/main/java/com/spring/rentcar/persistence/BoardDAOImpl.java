package com.spring.rentcar.persistence;


import com.spring.rentcar.domain.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

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
    public List<BoardVO> listBoard() throws Exception {

        return sqlSession.selectList(NAMESPACE+".listBoard");
    }
}
