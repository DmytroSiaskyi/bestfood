package com.bestfood.services;

import com.bestfood.entity.News;

public interface NewsService extends EntityService<News>{
    News findByTitle(String title);
}
