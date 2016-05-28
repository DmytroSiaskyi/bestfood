package com.bestfood.services;

import java.util.List;

public interface EntityService<T> {
    T find(Long id);
    void add(T entity);
    T update(T entity);
    void remove(Long id);
    List<T> list();
}
