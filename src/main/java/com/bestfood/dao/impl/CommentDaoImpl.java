package com.bestfood.dao.impl;

import com.bestfood.dao.CommentDao;
import com.bestfood.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class CommentDaoImpl extends DaoImpl<Comment> implements CommentDao{
    public CommentDaoImpl(){super(Comment.class);}

    @Override
    public List<Comment> getCommentList(Long id) {
        List<Comment> result = null;
        try{
            result = entityManager.createQuery("select a from Comment a where a.article.id =:articleId", Comment.class).setParameter("articleId",id).getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        return result;
    }
}
