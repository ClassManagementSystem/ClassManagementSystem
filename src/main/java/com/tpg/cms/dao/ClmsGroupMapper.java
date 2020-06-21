package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.model.ClmsClass;
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
            "        select count(*) from  group\n" +
            "        where group_id = group_id \n" +
            "        <if test=\"params.group_id!=null\">\n" +
            "            and article_id = #{params.group_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.group_name!=null\">\n" +
            "            and group_name like CONCAT('%', #{params.group_name}, '%')\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsArticle> page);

    //分页查询
    @Select("<script>"+
            "       select group_id, group_name, user_name, user_id, user_group_id, user_remark,user_sex,uesr_phone\n"+
            "       from clms_group inner join clms_user on clms_group.id = clms_user_group.id\n"+
            "         where group_id = group_id \n" +
            "        <if test=\"params.group_id!=null\">\n" +
            "            and group_id = #{params.group_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.user_group_id!=null\">\n" +
            "            and user_group_id = #{params.user_group_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.user_id!=null\">\n" +
            "            and user_id= #{params.user_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.group_name!=null\">\n" +
            "            and group_name like CONCAT('%', #{params.group_name}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params. user_name!=null\">\n" +
            "            and  user_name like CONCAT('%', #{params. user_name}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.clms_ user_remark!=null\">\n" +
            "            and clms_ user_remark like CONCAT('%', #{params.clms_ user_remark}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.user_sex!=null\">\n" +
            "            and user_sex like CONCAT('%', #{params.user_sex}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.uesr_phone!=null\">\n" +
            "            and uesr_phone= #{params.uesr_phone}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}"+
            "</script>")
    List<ClmsArticle> getByPage(Page<ClmsArticle> page);

}