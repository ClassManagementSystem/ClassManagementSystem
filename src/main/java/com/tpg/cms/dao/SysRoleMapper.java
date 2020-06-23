package com.tpg.cms.dao;

import com.tpg.cms.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int deleteByIds(List<String> ids);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    List<SysRole> selectList(String roleName);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectBatchIds(List<String> roleIdList);
}