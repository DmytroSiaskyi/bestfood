package com.bestfood.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "poll")
public class Poll {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 128, nullable=false)
    private String question;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Answer> answerList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswerToList(Answer answer){
        answerList.add(answer);
    }
}
