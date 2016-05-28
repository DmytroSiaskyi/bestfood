package com.bestfood.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {

    private Long id;
    private String login;
    private String email;
    private String comment;
    private String created;

    public MessageDto(){}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}
    public String getCreated() {return created;}
    public void setCreated(String created) {this.created = created;}
}
