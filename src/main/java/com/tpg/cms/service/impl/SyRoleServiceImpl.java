package com.tpg.cms.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpg.cms.dao.SysRoleMapper;
import com.tpg.cms.dao.SysUserRoleMapper;
import com.tpg.cms.model.SysRole;
import com.tpg.cms.model.SysUserRole;
import com.tpg.cms.service.SyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class SyRoleServiceImpl implements SyRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;


    //根据用户获取角色数据
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<SysRole> allRolesList =roleMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<SysUserRole> existUserRoleList = userRoleMapper.selectByUserId(userId);

        List<String> existRoleList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        //对角色进行分类
        List<SysRole> assignRoles = new ArrayList<SysRole>();
        for (SysRole role : allRolesList) {
            //已分配
            if(existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    //根据用户分配角色
    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
        userRoleMapper.removeByUserId(userId);

        List<SysUserRole> userRoleList = new ArrayList<>();
        for(String roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
            System.out.println(userRoleList);
        }
        userRoleMapper.saveBatch(userRoleList);
    }

    @Override
    public List<SysRole> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<SysUserRole> userRoleList = userRoleMapper.selectByUserId(id);
        List<String> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<SysRole> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = roleMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }

    @Override
    public PageInfo<SysRole> getByPage(Integer pageNum, Integer pageSize, String roleName) {
        //用插件进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> roles = roleMapper.selectList(roleName);
        PageInfo<SysRole> pageInfo = new PageInfo<>(roles);
        return pageInfo;
    }

    @Override
    public SysRole getById(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(SysRole role) {
        return roleMapper.insert(role);
    }

    @Override
    public int updateById(SysRole role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int removeById(String id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeByIds(List<String> idList) {
        return roleMapper.deleteByIds(idList);
    }
}
