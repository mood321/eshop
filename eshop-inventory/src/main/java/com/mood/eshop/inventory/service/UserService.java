package com.mood.eshop.inventory.service;

import com.mood.eshop.inventory.model.User;

public interface UserService {
    User findUserInfo();
    User getCachedUserInfo();
}
