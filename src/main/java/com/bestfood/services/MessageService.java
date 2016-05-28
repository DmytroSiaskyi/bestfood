package com.bestfood.services;

import com.bestfood.entity.Message;

import java.util.List;

public interface MessageService extends EntityService<Message>{
    public long count();
    public List<Message> list(int start, int limit);
}
