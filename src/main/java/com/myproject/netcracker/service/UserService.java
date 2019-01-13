package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.User;

import java.util.List;

public interface UserService {
    User findByLogin(String login);
    void saveUser(User user);
    List<User> findAllUsers();
    boolean isUserExist(User user);
    void updateUser(User user);

}
