package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsUser;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ClmsUserMapper {
    /**
     * 添加用户
     * @param clmsUser
     * @return
     */
    @Insert("insert into clms_user (user_id, user_name, user_password," +
            "      user_college_id, user_class_id, user_group_id," +
            "      user_avatar, user_birthday, user_phone," +
            "      user_sex, user_remark, status)" +
            "values (#{user_id}, #{user_name}, #{user_password}," +
            "      #{user_college_id}, #{user_class_id}, #{user_group_id}," +
            "      #{user_avatar}, #{user_birthday}, #{user_phone}," +
            "      #{user_sex}, #{user_remark}, #{status})")
    boolean save(ClmsUser clmsUser);

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Select("select * from clms_user where user_id = #{id}")
    ClmsUser getById(Integer id);


    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @Update("delete from clms_user where user_id = #{id}")
    boolean deletedById(Integer id);

    /**
     * 更新用户信息
     * @param clmsUser
     */
    @Update("update clms_user " +
            "    set user_name = #{user_name},\n" +
            "      user_password = #{user_password},\n" +
            "      user_college_id = #{user_college_id},\n" +
            "      user_class_id = #{user_class_id},\n" +
            "      user_group_id = #{user_group_id},\n" +
            "      user_avatar = #{user_avatar},\n" +
            "      user_birthday = #{user_birthday},\n" +
            "      user_phone = #{user_phone},\n" +
            "      user_sex = #{user_sex},\n" +
            "      user_remark = #{user_remark},\n" +
            "      status = #{status}\n" +
            "    where user_id = #{user_id}")
    public boolean update(ClmsUser clmsUser);

    /**
     * 分页查询用户信息
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select * from clms_user " +
            "        where 1 = 1 " +
            "        <if test=\"params.college_id != null\"> " +
            "            and user_college_id = #{params.college_id} " +
            "        </if> " +
            "        <if test=\"params.class_id != null\"> " +
            "            and user_college_id = #{params.class_id} " +
            "        </if> " +
            "        <if test=\"params.group_id != null\"> " +
            "            and user_college_id = #{params.group_id} " +
            "        </if> " +
            "        <if test=\"params.name != null\"> " +
            "            and user_name like CONCAT('%', #{params.name}, '%') " +
            "        </if> " +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsUser> getByPage(Page<ClmsUser> page);


    /**
     * 查询分页用户数据总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from  clms_user " +
            "        where 1 = 1 " +
            "        <if test=\"params.college_id != null\"> " +
            "            and user_college_id = #{params.college_id} " +
            "        </if> " +
            "        <if test=\"params.class_id != null\"> " +
            "            and user_college_id = #{params.class_id} " +
            "        </if> " +
            "        <if test=\"params.group_id != null\"> " +
            "            and user_college_id = #{params.group_id} " +
            "        </if> " +
            "        <if test=\"params.name != null\"> " +
            "            and user_name like CONCAT('%', #{params.name}, '%') " +
            "        </if> " +
            "</script>")
    public int getCountUser(Page<ClmsUser> page);

    /**
     * 根据用户username查询用户
     * @param username
     * @return
     */
    @Select("select * from clms_user where user_name = #{userName}")
    ClmsUser selectByUserName(String username);
}