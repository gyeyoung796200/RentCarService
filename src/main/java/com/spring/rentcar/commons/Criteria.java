package com.spring.rentcar.commons;

public class Criteria {

    private int page; //페이지 번호
    private int perPageNum; //한 페이지당 보여지는 글의 개수


    //기본 생성자로 Criteria가 생성될때 page와 perPageNum의 값이 셋팅된다
    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {

        if(page <= 0){
            page = 1;
        }
        this.page = page;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {

        if(perPageNum <= 0 || perPageNum > 100 ){
            perPageNum = 10;
        }
        this.perPageNum = perPageNum;
    }

    //게시글의 시작위치를 지정하는 메서드
    public int getPageStart(){

        return (this.page -1)* 10;
    }


    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}
