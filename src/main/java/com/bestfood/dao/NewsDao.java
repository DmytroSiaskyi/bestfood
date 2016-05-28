package com.bestfood.dao;

import com.bestfood.entity.News;

public interface NewsDao extends Dao<News>{
    News findByTitle(String title);
}
