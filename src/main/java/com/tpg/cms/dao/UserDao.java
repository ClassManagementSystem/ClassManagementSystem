package com.tpg.cms.dao;

import com.tpg.cms.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, collegeid, classid, groupid, avatar, birthday, status, phone, sex, remark) values(#{username}, #{password}, #{collegeid}, #{classid}, #{groupid}, #{avatar}, #{birthday}, #{status}, #{phone}, #{sex},#{remark})")
    int save(SysUser user);

    @Select("select * from sys_user t where t.id = #{id}")
    SysUser getById(Long id);

    @Select("select * from sys_user t where t.username = #{username}")
    SysUser getUser(String username);

    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int changePassword(@Param("id") Long id, @Param("password") String password);

   /* Integer count(@Param("params") Map<String, Object> params);

    List<SysUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                       @Param("limit") Integer limit);

    @Delete("delete from sys_role_user where userId = #{userId}")
    int deleteUserRole(Long userId);

    int saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    int update(SysUser user);*/
}
