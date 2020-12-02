package com.spring.rentcar.domain;

import java.util.Date;

public class ReplyVO {

    private int replyNo; //댓글번호
    private int bno; //게시판의 번호 참조
    private String replyText; //댓글 내용
    private String replyWriter; //댓글 작성자
    private Date regDate;
    private Date updateDate;

    @Override
    public String toString() {
        return "ReplyVO{" +
                "replyNo=" + replyNo +
                ", bno=" + bno +
                ", replyText='" + replyText + '\'' +
                ", replyWriter='" + replyWriter + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                '}';
    }

    public int getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(int replyNo) {
        this.replyNo = replyNo;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public String getReplyWriter() {
        return replyWriter;
    }

    public void setReplyWriter(String replyWriter) {
        this.replyWriter = replyWriter;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
