package com.tpg.cms.dao;


import com.tpg.cms.model.ClmsUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClmsUserRoleMapper {
    @Select("SELECT * FROM clms_user_role WHERE user_id = #{userId}")
    List<ClmsUserRole> listByUserId(Integer userId);
}