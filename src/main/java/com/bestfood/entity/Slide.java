package com.bestfood.entity;

import javax.persistence.*;

@Entity
@Table(name = "slide")
public class Slide {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="image", length = 128, nullable=false)
    private String imageUrl;
    @Column(name="text", length = 256, nullable=false)
    private String text;
    @Column(name="link", length = 128, nullable=false)
    private String link;
    @Column(name="order_id")
    private Integer orderId;
    @Column(name="frequency")
    private Integer frequency;

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Long getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
