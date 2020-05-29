package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysPermission {
    private String id;

    private String pid;

    private String name;

    private Byte type;

    private String permissionValue;

    private String path;

    private String component;

    private String icon;

    private Byte status;

    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer level;

    private List<SysPermission> children;

    private boolean isSelect;

}