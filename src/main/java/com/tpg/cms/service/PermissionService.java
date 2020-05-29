package com.tpg.cms.service;

import com.alibaba.fastjson.JSONObject;
import com.tpg.cms.model.SysPermission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 */
public interface PermissionService {

    int updateById(SysPermission permissionId);

    int save(SysPermission permissionId);

    //获取全部菜单
    List<SysPermission> queryAllMenu();

    //根据角色获取菜单
    List<SysPermission> selectAllMenu(String roleId);

    //给角色分配权限
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    //递归删除菜单
    void removeChildById(String id);

    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);
}
