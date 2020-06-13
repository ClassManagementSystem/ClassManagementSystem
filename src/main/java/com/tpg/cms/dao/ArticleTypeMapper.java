package com.tpg.cms.dao;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ArticleType;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleTypeMapper {

    // 新 增
    @Insert("insert into clms_type(type_name) " +
            "values (#{type_name})")
    void save(ArticleType articleType);

    // 根 据 id 删 除
    @Delete("delete from clms_type where type_id = #{id}")
    void deleteById(Integer id);

    // 根 据 id 查 询
    @Select("select * from clms_type where type_id = #{id}")
    ArticleType getById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_type set type_id = #{type_id}\n" +
            "        <if test=\"type_name != null and type_name != ''\">\n" +
            "            ,type_name = #{type_name}\n" +
            "        </if>\n" +
            "        where type_id = #{type_id}" +
            "</script>")
    void update(ArticleType articleType);

    // 分 页 查 询
    @Select("<script>" +
            "        select type_id, type_name, count(*) as article_count\n"+
            "        from clms_type, clms_article\n"+
            "        where type_id = article_type\n"+
            "        <if test=\"params.type_id!=null\">\n" +
            "            and type_id = #{params.type_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.type_name!=null\">\n" +
            "            and type_name like CONCAT('%', #{params.type_name}, '%')\n" +
            "        </if>\n" +
            "        group by type_id\n"+
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ArticleType> getByPage(Page<ArticleType> page);

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_type\n" +
            "        where type_id = type_id \n" +
            "        <if test=\"params.type_id!=null\">\n" +
            "            and type_id = #{params.type_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.type_name!=null\">\n" +
            "            and type_name like CONCAT('%', #{params.type_name}, '%')\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ArticleType> page);

    //查询各类型对应的文章数以及占比
    @Select("<script>" +
            "        select type_name, type_count," +
            "        type_count/(select count(*) from clms_type) as percent" +
            "        from clms_type\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "</script>")
    List<Map> getArticleTypeCountInfo(Page<ArticleStatistics> page);

}
