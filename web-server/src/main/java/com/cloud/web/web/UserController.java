package com.cloud.web.web;

import com.cloud.util.utils.Constants;
import com.cloud.util.utils.MD5;
import com.cloud.web.domain.User;
import com.cloud.web.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author xiaosa
 * 用户相关
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(@RequestParam(value = "userName") String userName,
                           @RequestParam(value = "password") String password,
                           HttpServletRequest request){
        User user = new User();
        //校验信息
        if(StringUtils.isNotBlank(userName)){
            return "账号不能为空";
        }
        if(StringUtils.isNotBlank(password)){
            return "密码不能为空";
        }
        //查询该账号是否已经存在
         user = userService.getUserByUserName(userName);
        if(user !=null){
            return "该账号已经被注册";
        }
        //使用MD5加密
        String passwordMD5 = MD5.encryptionStr(password);
        user.setPassword(passwordMD5);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(true);
        user.setInvalid(false);
        //插入数据库
        userService.insert(user);
        //保存到session
        request.setAttribute(Constants.USER,user);
        return "error";
    }
}
