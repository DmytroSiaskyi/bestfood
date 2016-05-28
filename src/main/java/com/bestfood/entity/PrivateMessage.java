package com.bestfood.entity;

import com.bestfood.dto.PrivateMessageDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "private_message")
public class PrivateMessage {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User addressee;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @Column(name = "text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created")
    private Date created;

    @Column(name = "read")
    private Boolean read;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAddressee() {
        return addressee;
    }

    public void setAddressee(User addressee) {
        this.addressee = addressee;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
    public PrivateMessageDto getPrivateMessageDto(){
        PrivateMessageDto result = new PrivateMessageDto();
        result.setText(this.getText());
        result.setRead(this.getRead());
        result.setWrited(this.getCreated());
        result.setAuthor(this.getAuthor().getLogin());
        result.setAdressee(this.getAddressee().getLogin());
        return result;
    }
    public String validate(){
        String error = null;
        if(this.getText().length()<6){
            error = "Довжина повідомлення має бути більше 6 символів!";
        }
        return error;
    }
}
