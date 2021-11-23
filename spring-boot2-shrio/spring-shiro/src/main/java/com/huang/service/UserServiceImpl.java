package com.huang.service;

import com.huang.mapper.Usermapper;
import com.huang.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @authour huang
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private Usermapper usermapper;

    @Override
    public user queryUserByName(String username) {
        return usermapper.queryUserByName(username);
    }
}
