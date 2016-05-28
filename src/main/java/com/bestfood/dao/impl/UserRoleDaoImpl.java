package com.bestfood.dao.impl;

import com.bestfood.dao.UserRoleDao;
import com.bestfood.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends DaoImpl<UserRole> implements UserRoleDao {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }

    /**
     * Find role by role name
     *
     * @param name String as param
     * @return     {@link UserRole} entity
     */
    @Override
    public UserRole findByName(String name) {
        UserRole role = entityManager.createQuery("select r from UserRole r where r.name = 'ROLE_USER'", UserRole.class)
                //.setParameter("name", name)
                .getSingleResult();
        return role;
    }
}
