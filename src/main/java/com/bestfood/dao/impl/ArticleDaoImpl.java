package com.bestfood.dao.impl;

import com.bestfood.dao.ArticleDao;
import com.bestfood.entity.Article;
import com.bestfood.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ArticleDaoImpl extends DaoImpl<Article> implements ArticleDao{
    public ArticleDaoImpl(){super(Article.class);}

    @Override
    public List<Article> getArticleListByRole(Integer roleState) {
        if (roleState == null){
            roleState = 2;
        }
        List<Article> result = null;
        try {
            result = entityManager.createNamedQuery("findListArticleByRole",Article.class)
                    .setParameter("roleState",roleState)
                    .getResultList();
        } catch (NoResultException e){
            return result;
        }
        return result;
    }

    @Override
    public List<Article> getArticleListByRoleCat(Integer roleState, Long category) {
        if (roleState == null){
            roleState = 2;
        }
        List<Article> result = null;
        try {
            result = entityManager.createNamedQuery("findListByCat",Article.class)
                    .setParameter("roleState",roleState)
                    .setParameter("catId", category)
                    .getResultList();
        } catch (NoResultException e){
            return result;
        }
        return result;
    }

    @Override
    public Article findByName(String name) {
        Article result = null;
        try{
            result = entityManager.createQuery("SELECT a FROM Article a WHERE a.name = :articleName",Article.class).setParameter("articleName", name).getSingleResult();
        }catch(NoResultException e){
            return result;
        }
        return result;
    }

    @Override
    public void setNewCategory(Long lastCat, Category newCat) {
        try{
            entityManager.createQuery("UPDATE Article SET category =:cat WHERE category.id =:lastCat").setParameter("cat", newCat).setParameter("lastCat",lastCat).executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
