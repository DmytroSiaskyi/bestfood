package com.bestfood.services;

import com.bestfood.entity.Slide;

import java.util.List;

public interface SlideService extends EntityService<Slide>{
    List<Slide> listOrdered();
    void setFrequency(int value);
}
