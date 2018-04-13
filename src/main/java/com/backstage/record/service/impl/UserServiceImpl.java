package com.backstage.record.service.impl;

import com.backstage.record.domain.User;
import com.backstage.record.repository.UserDao;
import com.backstage.record.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getUserAll() {
        return userDao.getAll();
    }

    @Override
    public int addUser(User user) {
        return userDao.add(user);
    }
}
