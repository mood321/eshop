package com.mood.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mood.eshop.inventory.dao.RedisDAO;
import com.mood.eshop.inventory.mapper.UserMapper;
import com.mood.eshop.inventory.model.User;
import com.mood.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mood321
 * @date 2020/8/5 21:33
 * @email 371428187@qq.com
 */
@Service
public class UserServiceImpl  implements UserService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisDAO redisDAO;

    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {
        redisDAO.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");

        String userJSON = redisDAO.get("cached_user_lisi");
        JSONObject userJSONObject = JSONObject.parseObject(userJSON);

        User user = new User();
        user.setName(userJSONObject.getString("name"));
        user.setAge(userJSONObject.getInteger("age"));

        return user;
    }
}