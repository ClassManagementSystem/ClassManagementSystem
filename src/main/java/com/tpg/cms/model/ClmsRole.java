package com.tpg.cms.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClmsRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    // 省略getter/setter
}
