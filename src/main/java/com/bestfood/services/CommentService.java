package com.bestfood.services;

import com.bestfood.dto.CommentDto;
import com.bestfood.entity.Comment;

import java.util.List;

public interface CommentService extends EntityService<Comment>{
    List<CommentDto> getCommentDtoList(Long id);
}
