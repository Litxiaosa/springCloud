package com.cloud.front.domain;

import lombok.Data;
import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.util.Date;

@Data
@Message
public class User implements Serializable {
    private Long id;

    private String name;

    private String moblie;

    private String password;

    private String email;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    private Boolean invalid;

    private static final long serialVersionUID = 1L;

}