package com.bestfood.services.impl;

import com.bestfood.dao.CommentDao;
import com.bestfood.dto.CommentDto;
import com.bestfood.entity.Comment;
import com.bestfood.services.CommentService;
import com.bestfood.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService, EntityService<Comment>{

    @Autowired
    private CommentDao commentDao;
    @Override
    public Comment find(Long id) {
        return commentDao.find(id);
    }

    @Transactional
    public void add(Comment entity) {
        commentDao.update(entity);
    }

    @Transactional
    public Comment update(Comment entity) {
        Comment comment = commentDao.update(entity);
        return comment;
    }

    @Transactional
    public void remove(Long id) {
        Comment comment = commentDao.find(id);
        commentDao.remove(comment);
    }

    @Override
    public List<Comment> list() {
        return commentDao.list();
    }

    @Override
    public List<CommentDto> getCommentDtoList(Long id) {
        List<Comment> entities = commentDao.getCommentList(id);
        List<CommentDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((comment)->result.add(comment.toCommentDto()));
        }
        return result;
    }
}
