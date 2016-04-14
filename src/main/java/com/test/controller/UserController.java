package com.test.controller;

import com.test.entity.User;
import com.test.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 35717 on 14.04.2016.
 */
@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    public UserController() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            Statement statement = dbConnection.createStatement();
            statement.execute("CREATE SCHEMA `test`;");
            statement.execute("CREATE TABLE `test`.`User`(`id` INT(8) NOT NULL AUTO_INCREMENT,`name` VARCHAR(25) NULL,`age`INT  NULL,`isAdmin` BIT NULL,`createdDate` TIMESTAMP NULL,PRIMARY KEY (`id`));");
            statement.execute("INSERT INTO test.User (name,age,isAdmin,createdDate) VALUES ('alex','22',true,'2010-09-15 19:34:41.0');");
            statement.execute("INSERT INTO test.User (name,age,isAdmin,createdDate) VALUES ('masha','19',false,'1990-01-29 07:34:19.0');");
            statement.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("UserController()");

    }

    @Autowired
    private UserService userService;

    @RequestMapping("createUser")
    public ModelAndView createUser(@ModelAttribute User user) {
        logger.info("Creating User. Data: "+user);
        return new ModelAndView("userForm");
    }

    @RequestMapping("editUser")
    public ModelAndView editUser(@RequestParam long id, @ModelAttribute User user) {
        logger.info("Updating the User for the Id "+id);
        user = userService.getUser(id);
        return new ModelAndView("userForm", "userObject", user);
    }

    @RequestMapping("saveUser")
    public ModelAndView saveUser(@ModelAttribute User user) {
        logger.info("Saving the User. Data : "+user);
        if(user.getId() == 0){
            userService.createUser(user);
        } else {
            userService.updateUser(user);
        }
        return new ModelAndView("redirect:getAllUsers");
    }

    @RequestMapping("deleteUser")
    public ModelAndView deleteUser(@RequestParam long id) {
        logger.info("Deleting the User. Id : "+id);
        userService.deleteUser(id);
        return new ModelAndView("redirect:getAllUsers");
    }

    @RequestMapping(value = {"getAllUsers", "/"})
    public ModelAndView getAllUsers() {
        logger.info("Getting the all Users.");
        List<User> userList = userService.getAllUsers();
        return new ModelAndView("userList", "userList", userList);
    }

    @RequestMapping("searchUser")
    public ModelAndView searchUser(@RequestParam("searchName") String searchName) {
        logger.info("Searching the User. User Names: "+searchName);
        List<User> userList = userService.getAllUsers(searchName);
        return new ModelAndView("userList", "userList", userList);
    }
}
