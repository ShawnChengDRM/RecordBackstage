package com.backstage.record.service;

import com.backstage.record.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUserAll();

    int addUser(User user);
}
