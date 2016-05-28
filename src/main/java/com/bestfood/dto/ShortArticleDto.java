package com.bestfood.dto;

import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShortArticleDto {
    private Long id;
    private String title;
    private String name;
    private String previewText;
    private Date created;
    private String author;
    private Boolean enable;
    private Integer access;
    private Integer orderId;

    public ShortArticleDto(){}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @XmlTransient
    public String getCreatedString(){
        if(created != null){
            try{
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                return  format.format(this.created);
            }catch(Exception e){
                return "";
            }
        }
        return "";
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public String getPreviewText() {
        return previewText;
    }
    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }
}