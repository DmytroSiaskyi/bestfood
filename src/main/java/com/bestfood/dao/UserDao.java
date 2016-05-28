package com.bestfood.dao;


import com.bestfood.entity.User;


public interface UserDao extends Dao<User>{
    User findByLogin(String login);
}
