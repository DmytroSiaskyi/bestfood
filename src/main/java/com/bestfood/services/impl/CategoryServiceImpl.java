package com.bestfood.services.impl;

import com.bestfood.dao.CategoryDao;
import com.bestfood.entity.Category;
import com.bestfood.services.CategoryService;
import com.bestfood.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService, EntityService<Category>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public Category find(Long id) {
        return categoryDao.find(id);
    }

    @Transactional
    public void add(Category entity) {
        categoryDao.update(entity);
    }

    @Transactional
    public Category update(Category entity) {
        Category category = categoryDao.update(entity);
        return category;
    }

    @Transactional
    public void remove(Long id) {
        Category category = categoryDao.find(id);
        categoryDao.remove(category);
    }

    @Override
    public List<Category> list() {
        return categoryDao.list();
    }

    @Override
    public Category findByName(String name) {
        return categoryDao.findByName(name);
    }
}
