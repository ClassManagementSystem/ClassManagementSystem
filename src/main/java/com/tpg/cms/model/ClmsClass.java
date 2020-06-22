package com.tpg.cms.model;

import lombok.Data;

@Data
public class ClmsClass {
    private Integer class_id;

    private String class_name;

    private Integer status;

    private Integer class_count;

    /*public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }*/
}