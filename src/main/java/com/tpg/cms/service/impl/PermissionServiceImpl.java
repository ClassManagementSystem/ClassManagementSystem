package com.tpg.cms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tpg.cms.dao.SysPermissionMapper;
import com.tpg.cms.dao.SysRolePermissionMapper;
import com.tpg.cms.dao.SysUserMapper;
import com.tpg.cms.helper.MemuHelper;
import com.tpg.cms.helper.PermissionHelper;
import com.tpg.cms.model.SysPermission;
import com.tpg.cms.model.SysRolePermission;
import com.tpg.cms.model.SysUser;
import com.tpg.cms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;
    
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public int updateById(SysPermission permissionId) {
        int result = permissionMapper.updateByPrimaryKey(permissionId);
        return result;
    }

    @Override
    public int save(SysPermission permissionId) {
        int result = permissionMapper.insert(permissionId);
        return result;
    }

    //获取全部菜单
    @Override
    public List<SysPermission> queryAllMenu() {
        List<SysPermission> permissionList = permissionMapper.selectListDesc();
        List<SysPermission> result = bulid(permissionList);
        return result;
    }

    //根据角色获取菜单
    @Override
    public List<SysPermission> selectAllMenu(String roleId) {
        List<SysPermission> allPermissionList = permissionMapper.selectListAsc();

        //根据角色id获取角色权限
        List<SysRolePermission> rolePermissionList = rolePermissionMapper.selectByRoleId(roleId);

        for (int i = 0; i < allPermissionList.size(); i++) {
            SysPermission permission = allPermissionList.get(i);
            for (int m = 0; m < rolePermissionList.size(); m++) {
                SysRolePermission rolePermission = rolePermissionList.get(m);
                if(rolePermission.getPermissionId().equals(permission.getId())) {
                    permission.setSelect(true);
                }
            }
        }


        List<SysPermission> permissionList = bulid(allPermissionList);
        return permissionList;
    }

    //给角色分配权限
    @Override
    public void saveRolePermissionRealtionShip(String roleId, String[] permissionIds) {

        rolePermissionMapper.removeByRoleId(roleId);
        List<SysRolePermission> rolePermissionList = new ArrayList<>();
        System.out.println("======================"+rolePermissionList);
        for(String permissionId : permissionIds) {
            if(StringUtils.isEmpty(permissionId)) continue;

            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionMapper.saveBatch(rolePermissionList);
    }

    //递归删除菜单
    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        this.selectChildListById(id, idList);

        idList.add(id);
        permissionMapper.deleteByIds(idList);
    }

    //根据用户id获取用户菜单
    @Override
    public List<String> selectPermissionValueByUserId(String id) {

        List<String> selectPermissionValueList = null;
        if(this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = permissionMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = permissionMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<SysPermission> selectPermissionList = null;
        if(this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = permissionMapper.selectListAsc();
        } else {
            selectPermissionList = permissionMapper.selectPermissionByUserId(userId);
        }

        List<SysPermission> permissionList = PermissionHelper.bulid(selectPermissionList);
        List<JSONObject> result = MemuHelper.bulid(permissionList);
        return result;
    }

    /**
     * 判断用户是否系统管理员
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        SysUser user = userMapper.selectByPrimaryKey(userId);

        if(null != user && "admin".equals(user.getUsername())) {
            return true;
        }
        return false;
    }

    /**
     *	递归获取子节点
     * @param id
     * @param idList
     */
    private void selectChildListById(String id, List<String> idList) {
        List<SysPermission> childList = permissionMapper.selectListPid(id);
        childList.stream().forEach(item -> {
            idList.add(item.getId());
            this.selectChildListById(item.getId(), idList);
        });
    }

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    private static List<SysPermission> bulid(List<SysPermission> treeNodes) {
        List<SysPermission> trees = new ArrayList<>();
        for (SysPermission treeNode : treeNodes) {
            if ("0".equals(treeNode.getPid())) {
                treeNode.setLevel(1);
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static SysPermission findChildren(SysPermission treeNode,List<SysPermission> treeNodes) {
        treeNode.setChildren(new ArrayList<SysPermission>());

        for (SysPermission it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
