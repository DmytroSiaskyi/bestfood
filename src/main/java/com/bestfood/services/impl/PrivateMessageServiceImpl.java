package com.bestfood.services.impl;


import com.bestfood.dao.PrivateMessageDao;
import com.bestfood.dto.PrivateMessageDto;
import com.bestfood.entity.PrivateMessage;
import com.bestfood.services.EntityService;
import com.bestfood.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("privateMessageService")
public class PrivateMessageServiceImpl implements PrivateMessageService, EntityService<PrivateMessage>{

    @Autowired
    private PrivateMessageDao privateMessageDao;
    @Override
    public PrivateMessage find(Long id) {
        return privateMessageDao.find(id);
    }

    @Transactional
    public void add(PrivateMessage entity) {
        privateMessageDao.update(entity);
    }

    @Transactional
    public PrivateMessage update(PrivateMessage entity) {
        PrivateMessage result = privateMessageDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        PrivateMessage entity = privateMessageDao.find(id);
        privateMessageDao.remove(entity);
    }

    @Override
    public List<PrivateMessage> list() {
        return privateMessageDao.list();
    }

    @Override
    public List<PrivateMessageDto> getDtoListOut(String userName) {
        List<PrivateMessage> entities = privateMessageDao.getUserWritedMessages(userName);
        List<PrivateMessageDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((message)->result.add(message.getPrivateMessageDto()));
        }
        return result;
    }

    @Override
    public List<PrivateMessageDto> getDtoListIn(String userName) {
        List<PrivateMessage> entities = privateMessageDao.getUserAdressedMessages(userName);
        List<PrivateMessageDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((message)->result.add(message.getPrivateMessageDto()));
        }
        return result;
    }
}
