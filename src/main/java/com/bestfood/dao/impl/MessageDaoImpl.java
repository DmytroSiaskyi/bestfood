package com.bestfood.dao.impl;

import com.bestfood.dao.MessageDao;
import com.bestfood.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageDaoImpl extends DaoImpl<Message> implements MessageDao{

    public MessageDaoImpl() {
        super(Message.class);
    }

    @Override
    public List<Message> list(int start, int limit) {
        List<Message> temp = null;
        List<Message> result = new ArrayList<>();
        //select * from employee limit 10 offset 5
        temp = entityManager.createQuery("select a FROM Message a",Message.class)
                .getResultList();
        for(int i = start - 1; i < start + limit - 1; i++){
            if(temp.size() > i) {
                result.add(temp.get(i));
            }
        }
        return result;
    }
}

