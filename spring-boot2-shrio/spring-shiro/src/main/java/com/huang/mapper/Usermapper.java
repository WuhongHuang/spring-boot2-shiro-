package com.huang.mapper;

import com.huang.pojo.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @authour huang
 */
@Mapper
@Repository
public interface Usermapper {

   // @Select("select * from ssmbuilt.t_user where username = #{username};")
    user queryUserByName(String username);

}
