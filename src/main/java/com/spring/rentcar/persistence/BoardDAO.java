package com.spring.rentcar.persistence;

import com.spring.rentcar.commons.Criteria;
import com.spring.rentcar.commons.SearchCriteria;
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

    //전체글 페이징
    public List<BoardVO> listPaging(SearchCriteria searchCriteria) throws Exception;

    //총 게시글 개수
    public int totalCount(SearchCriteria searchCriteria) throws Exception;



    //검색목록 페이징
    public List<BoardVO> listSearch(SearchCriteria searchCriteria) throws Exception;

    public int countSearchBoard(SearchCriteria searchCriteria) throws Exception;

}
