package com.xizi.service;


import com.xizi.dao.UserDao;
import com.xizi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Resource
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        //根据接收用户名密码查询数据库
        User user1 = userDao.login(user);
        if (user1!=null){
            return user1;
        }
        throw new RuntimeException("登录失败~~");
    }
}
