package com.spring.rentcar.persistence;

import com.spring.rentcar.domain.BoardVO;

import java.util.List;

public interface BoardDAO {

    //게시글 작성
    public void insertBoard(BoardVO boardVO) throws Exception;

    //게시글 읽기
    public BoardVO readBoard(int bno) throws Exception;

    //게시글 수정
    public void updateBoard(BoardVO boardVO) throws Exception;

    //게시글 삭제
    public void deleteBoard(int bno) throws Exception;

    //전체글 조회
    public List<BoardVO> listBoard() throws Exception;
}
