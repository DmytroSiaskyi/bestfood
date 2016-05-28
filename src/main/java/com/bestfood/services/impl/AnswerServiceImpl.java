package com.bestfood.services.impl;

import com.bestfood.dao.AnswerDao;
import com.bestfood.entity.Answer;
import com.bestfood.services.AnswerService;
import com.bestfood.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService, EntityService<Answer>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AnswerDao answerDao;

    @Override
    public Answer find(Long id) {
        return answerDao.find(id);
    }

    @Transactional
    public void add(Answer entity) {
        answerDao.update(entity);
    }

    @Transactional
    public Answer update(Answer entity) {
        Answer answer = answerDao.update(entity);
        return answer;
    }

    @Transactional
    public void remove(Long id) {
        Answer answer = answerDao.find(id);
        answerDao.remove(answer);
    }

    @Override
    public List<Answer> list() {
        return answerDao.list();
    }
}
