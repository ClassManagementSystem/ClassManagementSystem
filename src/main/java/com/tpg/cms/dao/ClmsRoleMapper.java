package com.tpg.cms.dao;


import com.tpg.cms.model.ClmsRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClmsRoleMapper {
    @Select("SELECT * FROM clms_role WHERE id = #{id}")
    ClmsRole selectById(Integer id);

    @Select("SELECT * FROM clms_role WHERE name = #{name}")
    ClmsRole selectByName(String name);

}
