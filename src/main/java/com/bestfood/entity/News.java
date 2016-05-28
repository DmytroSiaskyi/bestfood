package com.bestfood.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news")
public class News implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128, nullable=false)
    private String title;

    @Column(length = 128, nullable=false)
    private String photoRef;

    @Column(length = 1024, nullable=false)
    private String text;

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

    public String getPhotoRef() {
        return photoRef;
    }

    public void setPhotoRef(String photoRef) {
        this.photoRef = photoRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
