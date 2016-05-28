package com.bestfood.dao.impl;

import com.bestfood.dao.UserDao;
import com.bestfood.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDaoImpl extends DaoImpl<User> implements UserDao {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    public UserDaoImpl() {
        super(User.class);
    }

    /**
     * Find user by login
     *
     * @param login login value
     * @return      entity {@link User}
     */
    @Override
    public User findByLogin(String login) {
        User user;
        try {
            user = entityManager.createQuery("select u from User u where u.login = :login and u.enabled=true", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return user;
    }
}
