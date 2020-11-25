package com.spring.rentcar.service;


import com.spring.rentcar.domain.UserVO;
import com.spring.rentcar.persistence.UserDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;

    @Override
    public void insertUser(UserVO userVO) throws Exception {

        userDAO.insertUser(userVO);
    }

    @Override
    public UserVO readUser(String userId) throws Exception {

        return userDAO.readUser(userId);
    }

    @Override
    public UserVO readUserWithPw(String userId, String userPw) throws Exception {

        return userDAO.readUserWithPw(userId, userPw);
    }
}
