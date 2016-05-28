package com.bestfood.dao.impl;

import com.bestfood.dao.PollDao;
import com.bestfood.entity.Poll;
import org.springframework.stereotype.Repository;

@Repository
public class PollDaoImpl extends DaoImpl<Poll> implements PollDao{
    public PollDaoImpl(){super(Poll.class);}
}
