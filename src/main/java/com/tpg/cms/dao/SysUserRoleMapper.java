package com.tpg.cms.dao;

import com.tpg.cms.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int removeByUserId(String userId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    int saveBatch(List<SysUserRole> userRoleList);

    SysUserRole selectByPrimaryKey(String id);

    List<SysUserRole>  selectByUserId(String userId);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}