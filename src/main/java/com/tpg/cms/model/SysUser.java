package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SysUser {
    private String id;

    private String username;

    private String password;

    private String nickName;

    private String header;

    private String token;

    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}