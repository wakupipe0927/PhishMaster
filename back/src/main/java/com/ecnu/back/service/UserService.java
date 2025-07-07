package com.ecnu.back.service;

import com.ecnu.back.dao.UserDao;
import com.ecnu.back.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    // 0 means no user, 1 means wrong password, 2 means ok
    public int login(String email, String password) {
        if (userDao.existsByUsername(email)) {
            if (userDao.checkPassword(email, password)) {
                return 2;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    // 0 means email exists, 1 means register successfully
    public int register(String username, String email, String password) {
        if (userDao.existsByUsername(email)) {
            return 0;
        } else {
            userDao.register(new User(username, email, password));
            return 1;
        }
    }
}
