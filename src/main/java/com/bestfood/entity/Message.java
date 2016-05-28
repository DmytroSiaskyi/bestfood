package com.bestfood.entity;

import com.bestfood.models.EmailValidator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_login", length = 64, nullable = false)
    private String login;
    @Column(name = "email", length = 64, nullable = false)
    private String email;
    @Column(name = "comment", length = 1024, nullable = false)
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created")
    private Date created;
    @Column(name = "IP", length = 64, nullable = false)
    private String IP;
    @Column(name = "answer", length = 1024, nullable = true)
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String validate(){
        String error = null;
        EmailValidator emailValidator = new EmailValidator();
        if(!emailValidator.validate(email))
            error = "Не корректно введена адреса!";
        if(comment.length() <= 6)
            error = "Довжина повідомлення менше 6 символів!";
        if(login.isEmpty() || email.isEmpty() || comment.isEmpty())
            error = "Не заповнені всі поля!";
        return error;
    }
}
