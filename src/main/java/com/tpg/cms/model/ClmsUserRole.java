package com.tpg.cms.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClmsUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer user_id;

    private Integer role_id;

    // 省略getter/setter
}
