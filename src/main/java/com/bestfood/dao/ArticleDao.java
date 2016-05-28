package com.bestfood.dao;

import com.bestfood.entity.Article;
import com.bestfood.entity.Category;

import java.util.List;

public interface ArticleDao extends Dao<Article>{
    List<Article> getArticleListByRole(Integer roleState);
    List<Article> getArticleListByRoleCat(Integer roleState, Long category);
    Article findByName(String name);
    void setNewCategory(Long lastCat, Category newCat);
}
