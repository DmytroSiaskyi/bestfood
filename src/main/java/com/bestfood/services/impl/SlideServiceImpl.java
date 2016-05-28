package com.bestfood.services.impl;

import com.bestfood.dao.SlideDao;
import com.bestfood.entity.Slide;
import com.bestfood.services.EntityService;
import com.bestfood.services.SlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("slideService")
public class SlideServiceImpl implements SlideService, EntityService<Slide>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SlideDao slideDao;
    @Override
    public Slide find(Long id) {
        return slideDao.find(id);
    }

    @Transactional
    public void add(Slide entity) {
        slideDao.update(entity);
    }

    @Transactional
    public Slide update(Slide entity) {
        Slide slide = slideDao.update(entity);
        return slide;
    }

    @Transactional
    public void remove(Long id) {
        Slide sliide = slideDao.find(id);
        slideDao.remove(sliide);
    }

    @Override
    public List<Slide> list() {
        return slideDao.list();
    }

    @Override
    public List<Slide> listOrdered() {
        return slideDao.listOrdered();
    }

    @Override
    public void setFrequency(int value) {
        List<Slide> slideList =slideDao.list();
        for(int i =0; i < slideList.size(); i++){
            slideList.get(i).setFrequency(value);
            slideDao.update(slideList.get(i));
        }
    }
}
