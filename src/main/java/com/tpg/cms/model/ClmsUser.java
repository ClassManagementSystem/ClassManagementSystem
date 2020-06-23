package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClmsUser {
    //用户id
    private Integer user_id;

    //用户名
    private String user_name;

    //用户密码
    private String user_password;

    //学院id
    private Integer user_college_id;

    //班级id
    private Integer user_class_id;

    //组id
    private Integer user_group_id;

    //用户头像
    private String user_avatar;

    //用户生日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String user_birthday;

    //手机号
    private String user_phone;

    //性别
    private Integer user_sex;

    //备注/介绍
    private String user_remark;

    //状态
    private Integer status;


}