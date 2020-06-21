package com.tpg.cms.dao;

import com.tpg.cms.model.ArticleType;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.model.ClmsClass;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsClassMapper {
    // 新 增
    @Insert("insert into clms_class(class_name) " +
            "values (#{class_name})")
    void save(ClmsClass clmsClass);

    // 根 据 id 删 除
    @Delete("delete from clms_class where class_id = #{id}")
    void deleteById(Integer id);

    // 根 据 id 查 询
    @Select("select * from clms_class where class_id = #{id}")
    ClmsClass getById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_class set class_id = #{class_id}\n" +
            "        <if test=\"class_name != null and class_name != ''\">\n" +
            "            ,class_name = #{class_name}\n" +
            "        </if>\n" +
            "        where class_id = #{class_id}" +
            "</script>")
    void update(ClmsClass clmsClass);

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
            "       select class_id, class_name, group_id, group_name,user_name, user_id,user_group_id, user_class_id, user_remark,user_sex,uesr_phone\n"+
            "       from clms_class inner join clms_user on clms_class.class_id = clms_user_class.id\n"+
            "                         inner join clms_group on clms_group.group_id= clms_user_group_id"+
            "         where class_id = class_id \n" +
            "        <if test=\"params.class_id!=null\">\n" +
            "            and class_id = #{params.class_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.user_class_id!=null\">\n" +
            "            and user_class_id = #{params.user_class_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.user_id!=null\">\n" +
            "            and user_id= #{params.user_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.group_id!=null\">\n" +
            "            and group_id= #{params.group_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.class_name!=null\">\n" +
            "            and group_name like CONCAT('%', #{params.group_name}, '%')\n" +
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
            "         <if test=\"params.article_like!=null\">\n" +
            "            and article_like = #{params.article_like}\n" +
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