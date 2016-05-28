package com.bestfood.services.impl;


import com.bestfood.dao.MessageDao;
import com.bestfood.entity.Message;
import com.bestfood.services.EntityService;
import com.bestfood.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService, EntityService<Message>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageDao messageDao;

    @Override
    public Message find(Long id) {
        return messageDao.find(id);
    }

    @Transactional
    public void add(Message entity) {
        messageDao.update(entity);
    }

    @Transactional
    public Message update(Message entity) {
        Message message = messageDao.update(entity);
        return message;
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Message message = messageDao.find(id);
        messageDao.remove(message);
    }

    @Override
    public List<Message> list() {
        return messageDao.list();
    }

    @Override
    public long count(){
        return messageDao.count();
    }

    @Override
    public List<Message> list(int start, int limit){
        List<Message> result;
        result = messageDao.list(start, limit);
        return result;
    }
}
