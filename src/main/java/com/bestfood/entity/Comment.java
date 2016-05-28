package com.bestfood.entity;

import com.bestfood.dto.CommentDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;

    @Column(nullable = false, name = "text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created")
    private Date created;

    @Column(name = "IP", length = 64, nullable = false)
    private String IP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
    public CommentDto toCommentDto(){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(this.getId());
        commentDto.setCreated(this.getCreated());
        commentDto.setText(this.getText());
        commentDto.setArticleId(this.getArticle().getId());
        commentDto.setAuthor(this.getAuthor().getFirstName() + " " + this.getAuthor().getLastName());
        return  commentDto;
    }
}
