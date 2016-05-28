package com.bestfood.dto;


import java.util.Date;

public class PrivateMessageDto {
    private String author;
    private String adressee;
    private Date writed;
    private String text;
    private Boolean read;
    public PrivateMessageDto(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAdressee() {
        return adressee;
    }

    public void setAdressee(String adressee) {
        this.adressee = adressee;
    }

    public Date getWrited() {
        return writed;
    }

    public void setWrited(Date writed) {
        this.writed = writed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
