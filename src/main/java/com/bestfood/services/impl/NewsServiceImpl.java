package com.bestfood.services.impl;

import com.bestfood.dao.NewsDao;
import com.bestfood.entity.News;
import com.bestfood.services.EntityService;
import com.bestfood.services.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService, EntityService<News>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NewsDao newsDao;

    @Override
    public News findByTitle(String title) {
        return null;
    }

    @Override
    public News find(Long id) {
        return newsDao.find(id);
    }

    @Override
    public void add(News entity) {
        newsDao.update(entity);
    }

    @Override
    public News update(News entity) {
        News news = newsDao.update(entity);
        return news;
    }

    @Override
    public void remove(Long id) {
        News news = newsDao.find(id);
        newsDao.remove(news);
    }

    @Override
    public List<News> list() {
        return newsDao.list();
    }
}
