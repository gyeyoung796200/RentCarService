package com.spring.rentcar.commons;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PageMaker {

    private int totalCount; //총 게시글 개수
    private int startPage; //시작 페이지 번호
    private int endPage; //끝 페이지 번호
    private boolean prev; //이전페이지 표시
    private boolean next; //다음페이지 표시

    private int displayPageNum = 10; //한 화면에 보여지는 페이지 번호의 개수

    private Criteria criteria; // 현재 페이지값과 한페이지에 보여지는 글의 개수를 가지고있다

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }

    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() {

        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNum()));

        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;

        next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;

    }

    //여러 요소를 한번에 묶어서 이어서 보내준다
    public String makeQuery(int page){

        UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page",page).queryParam("perPageNum", criteria.getPerPageNum() ).build();

        return uriComponents.toUriString();
    }

    public String makeSearch(int page){

        UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
                                                                        .queryParam("perPageNum", criteria.getPerPageNum())
                                                                        .queryParam("searchType", ((SearchCriteria)criteria).getSearchType())
                                                                        .queryParam("keyWord", encoding(((SearchCriteria)criteria).getKeyWord())).build();

        return uriComponents.toUriString();
    }

    private String encoding(String keyWord){

        if(keyWord == null || keyWord.trim().length() == 0){
            return "";
        }
        try{
            return URLEncoder.encode(keyWord,"UTF-8");
        }catch (UnsupportedEncodingException e){
            return "";
        }
    }

    @Override
    public String toString() {
        return "PageMaker{" +
                "totalCount=" + totalCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", prev=" + prev +
                ", next=" + next +
                ", displayPageNum=" + displayPageNum +
                ", criteria=" + criteria +
                '}';
    }
}
