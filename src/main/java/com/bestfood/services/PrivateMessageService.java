package com.bestfood.services;

import com.bestfood.dto.PrivateMessageDto;
import com.bestfood.entity.PrivateMessage;

import java.util.List;

public interface PrivateMessageService extends EntityService<PrivateMessage>{
    List<PrivateMessageDto> getDtoListOut(String userName);
    List<PrivateMessageDto> getDtoListIn(String userName);
}
