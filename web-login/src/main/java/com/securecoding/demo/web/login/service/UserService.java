package com.securecoding.demo.web.login.service;

import com.securecoding.demo.web.login.dataaccess.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndPassword(String name, String password);

    Optional<User> getUserInfo(String userId);
}
