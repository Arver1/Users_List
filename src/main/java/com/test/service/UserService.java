package com.test.service;

import com.test.entity.User;

import java.util.List;

/**
 * Created by 35717 on 14.04.2016.
 */
public interface UserService {
    public long createUser(User user);
    public User updateUser(User user);
    public void deleteUser(long id);
    public List<User> getAllUsers();
    public User getUser(long id);
    public List<User> getAllUsers(String userName);
}