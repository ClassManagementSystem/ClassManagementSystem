package com.tpg.cms.service;

import com.github.pagehelper.PageInfo;
import com.tpg.cms.model.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface SyRoleService {

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

    //根据用户分配角色
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<SysRole> selectRoleByUserId(String id);

    PageInfo<SysRole> getByPage(Integer pageNum, Integer pageSize, String roleName);


    SysRole getById(String id);

    int save(SysRole role);

    int updateById(SysRole role);

    int removeById(String id);

    int removeByIds(List<String> idList);
}
