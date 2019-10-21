package com.community.service;

import com.community.mapper.UserMapper;
import com.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2019/10/19 22:19
 * @Created by Tengxq
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user1) {
        User user = userMapper.findByAccountId(user1.getAccountId());
       if (user == null ){
           user.setGmtCreate(System.currentTimeMillis());
           user.setGmtModified(user.getGmtCreate());
           userMapper.insertUser(user);
       }else
       {
           user.setGmtModified(System.currentTimeMillis());
           userMapper.updateUser(user);
       }
    }
}
