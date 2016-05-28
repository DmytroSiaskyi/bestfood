package com.bestfood.dao;

import com.bestfood.entity.Message;

import java.util.List;

public interface MessageDao extends Dao<Message>{
    List<Message> list(int start, int limit);
}
