package com.spring.rentcar.commons;

public class SearchCriteria extends Criteria {

    private String searchType; //검새어종류
    private String keyWord; //검색어

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
