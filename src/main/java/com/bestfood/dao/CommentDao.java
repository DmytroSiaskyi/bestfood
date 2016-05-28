package com.bestfood.dao;

import com.bestfood.entity.Comment;

import java.util.List;

public interface CommentDao extends Dao<Comment>{
    List<Comment> getCommentList(Long id);
}
