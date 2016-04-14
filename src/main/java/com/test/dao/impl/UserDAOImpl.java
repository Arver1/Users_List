package com.test.dao.impl;

import com.test.dao.UserDAO;

import com.test.entity.User;
import com.test.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 35717 on 14.04.2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    public UserDAOImpl() {
        System.out.println("UserDAOImpl");
    }

    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public long createUser(User user) {
        return (Long) hibernateUtil.create(user);
    }

    @Override
    public User updateUser(User user) {
        return hibernateUtil.update(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = new User();
        user.setId(id);
        hibernateUtil.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return hibernateUtil.fetchAll(User.class);
    }

    @Override
    public User getUser(long id) {
        return hibernateUtil.fetchById(id, User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers(String userName) {
        String query = "SELECT e.* FROM User e WHERE e.name like '%"+ userName +"%'";
        List<Object[]> userObjects = hibernateUtil.fetchAll(query);
        List<User> users = new ArrayList<User>();
        for(Object[] userObject: userObjects) {
            User user = new User();
            long id = Long.parseLong(String.valueOf(userObject[0]));
            String name = (String) userObject[1];
            int age = (Integer) userObject[2];
            boolean isAdmin=(Boolean)userObject[3];
            Timestamp createdDate = (Timestamp)userObject[4];
           user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setAdmin(isAdmin);
            user.setCreatedDate(createdDate);
            users.add(user);
        }
        System.out.println(users);
        return users;
    }
}