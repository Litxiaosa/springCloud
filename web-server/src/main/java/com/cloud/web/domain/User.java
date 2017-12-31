package com.cloud.web.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long id;

    private String name;

    private String mobile;

    private String password;

    private String email;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean invalid;

    private static final long serialVersionUID = 1L;
}