package com.bestfood.services.impl;

import com.bestfood.dao.PollDao;
import com.bestfood.entity.Poll;
import com.bestfood.services.EntityService;
import com.bestfood.services.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("pollService")
public class PollServiceImpl implements PollService, EntityService<Poll>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PollDao pollDao;
    @Override
    public Poll find(Long id) {
        return pollDao.find(id);
    }

    @Transactional
    public void add(Poll entity) {
        pollDao.update(entity);
    }

    @Transactional
    public Poll update(Poll entity) {
        Poll poll = pollDao.update(entity);
        return poll;
    }

    @Transactional
    public void remove(Long id) {
        Poll poll = pollDao.find(id);
        pollDao.remove(poll);
    }

    @Override
    public List<Poll> list() {
        return pollDao.list();
    }
}
