package com.bestfood.dao.impl;

import com.bestfood.dao.AnswerDao;
import com.bestfood.entity.Answer;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl extends DaoImpl<Answer> implements AnswerDao{
    public AnswerDaoImpl(){super(Answer.class);}
}
