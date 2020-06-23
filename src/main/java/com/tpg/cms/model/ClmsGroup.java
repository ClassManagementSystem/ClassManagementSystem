package com.tpg.cms.model;

import lombok.Data;

@Data
public class ClmsGroup {
    private Integer group_id;

    private String group_name;

    private Integer status;

    private Integer group_count;

    /*public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }*/
}