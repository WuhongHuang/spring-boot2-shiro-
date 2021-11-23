package com.huang.service;

import com.huang.controller.MyController;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @authour huang
 */
@SpringBootTest
//@MapperScan("com.huang.mapper")
class UserServiceImplTest {

    private static final transient Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads(){
        log.info(userService.queryUserByName("huang").toString());
    }

}