package com.bestfood.dao;

import com.bestfood.entity.UserRole;

public interface UserRoleDao extends Dao<UserRole>{
    UserRole findByName(String name);
}
