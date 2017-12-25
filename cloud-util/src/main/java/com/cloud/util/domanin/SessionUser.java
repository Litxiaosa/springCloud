package com.cloud.util.domanin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaosa
 * Data: lombok的注解，免写get/set.需要下载插件。具体用法可百度。
 */
@Data
public class SessionUser implements Serializable{

    private Integer id;

    private String mobile;

    private String name;

    private String email;

    private Integer userType;

    //等等。。
}
