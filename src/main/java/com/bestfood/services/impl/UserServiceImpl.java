package com.bestfood.services.impl;

import com.bestfood.dao.UserDao;
import com.bestfood.dto.UserDto;
import com.bestfood.entity.User;
import com.bestfood.services.EntityService;
import com.bestfood.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, EntityService<User> {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByLogin(username);
        if(user == null)
            throw new UsernameNotFoundException("User "+username+" not found");
        return UserDto.create(user);
    }

    public User find(Long id) {
        return userDao.find(id);
    }

    @Transactional
    public void add(User user) {
        userDao.update(user);
    }

    @Transactional
    public User update(User user) {
        User result = userDao.update(user);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        User user = userDao.find(id);
        userDao.remove(user);
    }

    public List<User> list() {
        return userDao.list();
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = this.list();
        if (users.isEmpty()){
            return null;
        }
        return UserDto.create(users);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
