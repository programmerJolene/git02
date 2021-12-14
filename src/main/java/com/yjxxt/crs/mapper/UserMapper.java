package com.yjxxt.crs.mapper;

import com.yjxxt.crs.base.BaseMapper;
import com.yjxxt.crs.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByUserName(String userName);


}