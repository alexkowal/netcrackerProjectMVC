package com.myproject.netcracker.service;

import com.myproject.netcracker.domain.User;
import com.myproject.netcracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }
    @Override
    public void updateUser(User user){}
}
