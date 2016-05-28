package com.bestfood.services.impl;

import com.bestfood.dao.ArticleDao;
import com.bestfood.dto.ShortArticleDto;
import com.bestfood.entity.Article;
import com.bestfood.entity.Category;
import com.bestfood.services.ArticleService;
import com.bestfood.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl  implements ArticleService, EntityService<Article>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleDao articleDao;


    @Override
    public Article find(Long id) {
        return articleDao.find(id);
    }

    @Transactional
    public void add(Article entity) {
        articleDao.update(entity);
    }

    @Transactional
    public Article update(Article entity) {
        Article article = articleDao.update(entity);
        return article;
    }

    @Transactional
    public void remove(Long id) {
        Article article = articleDao.find(id);
        articleDao.remove(article);
    }

    @Override
    public List<Article> list() {
        return articleDao.list();
    }

    @Override
    public List<ShortArticleDto> getShortArticleList(Integer roleState) {
        if (roleState == null){
            roleState = 2;
        }
        List<Article> entities = articleDao.getArticleListByRole(roleState);
        List<ShortArticleDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((article)->result.add(article.toshortArticleDto()));
        }
        return result;
    }

    @Override
    public List<ShortArticleDto> getShortArticleListCat(Integer roleState, Long id) {
        if (roleState == null){
            roleState = 2;
        }
        List<Article> entities = articleDao.getArticleListByRoleCat(roleState,id);
        List<ShortArticleDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((article)->result.add(article.toshortArticleDto()));
        }
        return result;
    }

    @Override
    public List<ShortArticleDto> getAllShortArticles() {
        List<Article> entities = articleDao.list();
        List<ShortArticleDto> result = new ArrayList<>();
        if(entities != null && !entities.isEmpty()){
            entities.stream().forEach((article)->result.add(article.toshortArticleDto()));
        }
        return result;
    }

    @Transactional
    public void updateArticlesCategories(Long id, Category newCat) {
        articleDao.setNewCategory(id,newCat);
    }

    @Override
    public Article findByName(String name) {
        return articleDao.findByName(name);
    }
}
