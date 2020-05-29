package com.tpg.cms.dao;

import com.tpg.cms.model.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(String id);

    int removeByRoleId(String roleId);

    int insert(SysRolePermission record);

    int saveBatch(List<SysRolePermission> rolePermissionList);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(String id);

    List<SysRolePermission> selectByRoleId(String roleId);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
}