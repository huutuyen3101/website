package com.example.webmvc.service;

import com.example.webmvc.model.User;

public interface UserService {
    public User findByUserName(String userName);
}
