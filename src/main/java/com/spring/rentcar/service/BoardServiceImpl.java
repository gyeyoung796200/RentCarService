package com.spring.rentcar.service;

import com.spring.rentcar.domain.BoardVO;
import com.spring.rentcar.persistence.BoardDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService  {

    @Inject
    private BoardDAO boardDAO;

    @Override
    public void insertBoard(BoardVO boardVO) throws Exception {
        boardDAO.insertBoard(boardVO);
    }

    @Override
    public BoardVO readBoard(int bno) throws Exception {
        return boardDAO.readBoard(bno);
    }

    @Override
    public void updateBoard(BoardVO boardVO) throws Exception {
        boardDAO.updateBoard(boardVO);
    }

    @Override
    public void deleteBoard(int bno) throws Exception {
        boardDAO.deleteBoard(bno);
    }

    @Override
    public List<BoardVO> listBoard() throws Exception {
        return boardDAO.listBoard();
    }
}
