package com.tpg.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class ClmsUser {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer userCollegeId;

    private Integer userClassId;

    private Integer userGroupId;

    private String userAvatar;

    @JsonFormat(locale = "yyyy-mm-dd")
    private Date userBirthday;

    private String userPhone;

    private Integer userSex;

    private String userRemark;

    private Integer status;

    private Collection<GrantedAuthority> authorities;
    public ClmsUser(String userName, String userPassword, Collection<GrantedAuthority> authorities) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    public ClmsUser(){

    }

    public ClmsUser(Integer userId, String userName, String userPassword){
        this.userId=userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserCollegeId() {
        return userCollegeId;
    }

    public void setUserCollegeId(Integer userCollegeId) {
        this.userCollegeId = userCollegeId;
    }

    public Integer getUserClassId() {
        return userClassId;
    }

    public void setUserClassId(Integer userClassId) {
        this.userClassId = userClassId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass()!= o.getClass()) return false;
        ClmsUser that = (ClmsUser) o;
        return Objects.equals(userId, that.userId);
    }
}