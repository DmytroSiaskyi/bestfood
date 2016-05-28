package com.bestfood.services;

import com.bestfood.entity.Category;

public interface CategoryService extends EntityService<Category>{
    Category findByName(String name);
}
