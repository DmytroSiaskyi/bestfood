package com.bestfood.dao;


import com.bestfood.entity.Category;

public interface CategoryDao extends Dao<Category>{
    Category findByName(String name);
}
