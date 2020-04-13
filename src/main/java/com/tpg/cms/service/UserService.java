package com.tpg.cms.service;

import com.tpg.cms.model.SysUser;

public interface UserService {
    /*SysUser saveUser(UserDto userDto);

    SysUser updateUser(UserDto userDto);*/

    SysUser getUser(String username);

    void changePassword(String username, String oldPassword, String newPassword);
}
