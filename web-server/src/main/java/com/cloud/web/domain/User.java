package com.cloud.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaosa
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String userName;

    private String mobile;

    private String password;

    private String email;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean invalid;

    private static final long serialVersionUID = 1L;

}