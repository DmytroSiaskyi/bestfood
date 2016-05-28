package com.bestfood.dao.impl;

import com.bestfood.dao.SlideDao;
import com.bestfood.entity.Slide;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class SlideDaoImpl extends DaoImpl<Slide> implements SlideDao {
    public SlideDaoImpl(){super(Slide.class);}

    @Override
    public List<Slide> listOrdered() {
        List<Slide> result = null;
        try{
            result = entityManager.createQuery("select a from Slide a ORDER BY a.orderId",Slide.class).getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
