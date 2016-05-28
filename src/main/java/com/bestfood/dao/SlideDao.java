package com.bestfood.dao;


import com.bestfood.entity.Slide;

import java.util.List;

public interface SlideDao extends Dao<Slide>{
    List<Slide> listOrdered();
}
