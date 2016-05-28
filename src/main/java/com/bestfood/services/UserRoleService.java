package com.bestfood.services;

import com.bestfood.entity.UserRole;

public interface UserRoleService extends EntityService<UserRole>{
    UserRole findByName(String name);
}
