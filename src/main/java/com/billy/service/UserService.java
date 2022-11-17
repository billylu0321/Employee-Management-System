package com.billy.service;

import com.billy.dao.UserMapper;
import com.billy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

   public User queryUserByName(String username){
       return userMapper.queryUserByName(username);
   }
}
