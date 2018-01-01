package com.cloud.web.service;

import com.cloud.web.domain.User;

/**
 * @author xiaosa
 */
public interface IUserService {

    /**
     * 把用户信息插入数据库
     * @param user
     */
    void insert(User user);

    /**
     * 查询该账号是否已经存在
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);
}
