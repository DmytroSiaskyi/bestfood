package com.bestfood.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 128, nullable=false)
    private String text;
    @Column(length = 128, nullable=false)
    private Integer selected;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
}
