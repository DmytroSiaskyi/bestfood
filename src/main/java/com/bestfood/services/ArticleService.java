package com.bestfood.services;

import com.bestfood.dto.ShortArticleDto;
import com.bestfood.entity.Article;
import com.bestfood.entity.Category;

import java.util.List;

public interface ArticleService extends EntityService<Article>{
    List<ShortArticleDto> getShortArticleList(Integer roleState);
    List<ShortArticleDto> getShortArticleListCat(Integer roleState, Long id);
    List<ShortArticleDto> getAllShortArticles();
    void updateArticlesCategories(Long id, Category newCat);
    Article findByName(String name);
}
