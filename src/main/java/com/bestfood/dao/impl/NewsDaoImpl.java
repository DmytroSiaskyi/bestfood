package com.bestfood.dao.impl;

import com.bestfood.dao.NewsDao;
import com.bestfood.entity.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends DaoImpl<News> implements NewsDao {

    public NewsDaoImpl() {
        super(News.class);
    }
    @Override
    public News findByTitle(String title) {
        return null;
    }
}
