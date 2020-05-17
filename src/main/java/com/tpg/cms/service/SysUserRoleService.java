package com.tpg.cms.service;


import com.tpg.cms.dao.ClmsUserRoleMapper;
import com.tpg.cms.model.ClmsUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {
    @Autowired
    private ClmsUserRoleMapper userRoleMapper;

    public List<ClmsUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}