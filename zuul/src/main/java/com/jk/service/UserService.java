package com.jk.service;

import com.jk.model.UserBean;

import javax.servlet.http.HttpSession;

public interface UserService {
    String login(UserBean user, HttpSession session);
}
