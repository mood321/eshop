package com.mood.eshop.cache.mapper;

import com.mood.eshop.cache.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    public User findUserInfo();
}
