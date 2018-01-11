package com.cloud.web.dao;

import com.cloud.web.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 查询该账号是否已经存在
     * @param userName
     * @return
     */
    @Select("SELECT * FROM USER WHERE user_name = ${userName}")
    @ResultMap("BaseResultMap")
    User getUserByUserName(@Param("userName") String userName);
}