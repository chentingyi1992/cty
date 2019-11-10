package com.jk.service.impl;

import com.jk.mapper.UserDao;
import com.jk.model.UserBean;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String login(UserBean user, HttpSession session) {
        //验证用户名是否存在
        UserBean user1 = userDao.queryById(user.getName());
        if(user1==null){
            return "用户名不存在";
        }
        //验证密码是否正确
        if(!user1.getPassword().equals(user.getPassword())){
            return "密码错误";
        }
        session.setAttribute("user",user1);
        return "登录成功";
    }
}
