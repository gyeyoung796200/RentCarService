package com.spring.rentcar.service;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.commons.SearchCriteria;
import com.spring.rentcar.domain.BoardVO;

import java.util.List;

public interface BoardService {

    //게시글 작성
    public void insertBoard(BoardVO boardVO) throws Exception;

    //게시글 읽기
    public BoardVO readBoard(int bno) throws Exception;

    //게시글 수정
    public void updateBoard(BoardVO boardVO) throws Exception;

    //게시글 삭제
    public void deleteBoard(int bno) throws Exception;

    //검색목록 페이징
    public List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception;

    //총게시글 조회
    public int countSearchBoard(SearchCriteria searchCriteria) throws Exception;

    //조회수 증가
    public void viewCntBoard(int bno) throws Exception;
}
