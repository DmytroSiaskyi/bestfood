package com.bestfood.dao.impl;


import com.bestfood.dao.CategoryDao;
import com.bestfood.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CategoryDaoImpl extends DaoImpl<Category> implements CategoryDao{
    public CategoryDaoImpl(){super(Category.class);}

    @Override
    public Category findByName(String name) {
        Category category = null;
        try{
            category = entityManager.createQuery("select a from Category a where a.name =:name", Category.class).setParameter("name",name).getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return category;
    }
}
