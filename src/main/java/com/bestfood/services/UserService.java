package com.bestfood.services;

import com.bestfood.dto.UserDto;
import com.bestfood.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, EntityService<User>{
    List<UserDto> findAll();
    User findByLogin(String login);
}
