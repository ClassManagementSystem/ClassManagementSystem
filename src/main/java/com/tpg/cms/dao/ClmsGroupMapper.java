package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsGroup;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsGroupMapper {
    // 新 增
    @Insert("insert into clms_group(group_name) " +
            "values (#{group_name})")
    void save(ClmsGroup clmsGroup);

    // 根 据 id 删 除
    @Delete("delete from clms_group where group_id = #{id}")
    void deleteById(Integer id);

    // 根 据 id 查 询
    @Select("select * from clms_group where group_id = #{id}")
    ClmsGroup getById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_group set groups_id = #{group_id}\n" +
            "        <if test=\"group_name != null and group_name != ''\">\n" +
            "            ,group_name = #{group_name}\n" +
            "        </if>\n" +
            "        where group_id = #{group_id}" +
            "</script>")
    void update(ClmsGroup clmsGroup);

   // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from clms_group\n" +
            "        where group_id = group_id \n" +
            "        <if test=\"params.group_id!=null\">\n" +
            "            and article_id = #{params.group_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.group_name!=null\">\n" +
            "            and group_name like CONCAT('%', #{params.group_name}, '%')\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsGroup> page);

    //分页查询
    @Select("<script>"+
            "       select group_id, group_name, (select count(*) from clms_user\n" +
            "        where clms_group.group_id = clms_user.user_group_id ) as group_count\n"+
            "        from clms_group\n" +
            "        where 1=1\n"+
            "        <if test=\"params.group_name!=null\">\n" +
            "            and group_name like CONCAT('%', #{params.group_name}, '%')\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}"+
            "</script>")
    List<ClmsGroup> getByPage(Page<ClmsGroup> page);

    @Select("select * from clms_group")
    List<ClmsGroup> getAll();

}