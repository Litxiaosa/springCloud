package com.cloud.web.service.impl;

import com.cloud.web.dao.UserMapper;
import com.cloud.web.domain.User;
import com.cloud.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaosa
 */
@Service
public class UserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    /**
     * 把用户信息插入数据库
     * @param user
     */
    @Override
    public void insert(User user) {
        userMapper.insertSelective(user);
    }

    /**
     * 查询该账号是否已经存在
     * @param userName
     * @return
     */
    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
}
