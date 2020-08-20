package com.mood.eshop.inventory.mapper;

import com.mood.eshop.inventory.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    public User findUserInfo();
}
