package com.bestfood.dao;

import com.bestfood.entity.PrivateMessage;

import java.util.List;

public interface PrivateMessageDao extends Dao<PrivateMessage>{
    List<PrivateMessage> getUserAdressedMessages(String userName);
    List<PrivateMessage> getUserWritedMessages(String userName);
}
