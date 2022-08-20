package com.atguigu.service;

import com.atguigu.beans.User;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vanas
 * @create 2020-04-30 7:35 下午
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired  //Spring自动给userMapper装配值
    private UserMapper userMapper ;

    @Override
    public int registUser(User user) {
        //处理业务
        //把数据通过Mapper插入到数据库
        int insert = userMapper.insert(user);
        return insert;
    }
}
