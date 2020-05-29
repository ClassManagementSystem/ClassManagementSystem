package com.tpg.cms.dao;

import com.tpg.cms.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int deleteByIds(List<String> ids);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    //从数据库中取出用户信息
    SysUser selectByUsername(String username);

    List<SysUser> selectList(String username);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}