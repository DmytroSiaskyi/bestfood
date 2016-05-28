package com.bestfood.dao.impl;

import com.bestfood.dao.PrivateMessageDao;
import com.bestfood.entity.PrivateMessage;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PrivateMessageDaoImpl extends DaoImpl<PrivateMessage> implements PrivateMessageDao{
    public PrivateMessageDaoImpl(){super(PrivateMessage.class);}

    @Override
    public List<PrivateMessage> getUserAdressedMessages(String userName) {
        List<PrivateMessage> result = null;
        try{
            result = entityManager.createQuery("select a from PrivateMessage a WHERE a.addressee.login =:userName", PrivateMessage.class).setParameter("userName",userName).getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<PrivateMessage> getUserWritedMessages(String userName) {
        List<PrivateMessage> result = null;
        try{
            result = entityManager.createQuery("select a from PrivateMessage a WHERE a.author.login =:userName", PrivateMessage.class).setParameter("userName",userName).getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        return result;
    }
}
