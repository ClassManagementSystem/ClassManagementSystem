package com.tpg.cms.dao;

import com.tpg.cms.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int deleteByIds(List<String> ids);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    List<SysPermission> selectListDesc();

    List<SysPermission> selectListAsc();

    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<SysPermission> selectPermissionByUserId(String userId);

    List<SysPermission> selectListPid(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}