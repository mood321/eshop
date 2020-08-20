package com.mood.eshop.inventory.controller;

import com.mood.eshop.inventory.model.User;
import com.mood.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mood321
 * @date 2020/8/5 21:32
 * @email 371428187@qq.com
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userService.findUserInfo();
        return user;
    }
    @RequestMapping("/getCachedUserInfo")
    @ResponseBody
    public User getCachedUserInfo() {
        User user = userService.getCachedUserInfo();
        return user;
    }

}