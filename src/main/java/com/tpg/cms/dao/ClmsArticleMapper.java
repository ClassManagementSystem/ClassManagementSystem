package com.tpg.cms.dao;

import com.tpg.cms.model.ArticleStatistics;
import com.tpg.cms.model.ClmsArticle;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ClmsArticleMapper {

    // 新 增
    @Insert("insert into clms_article(article_title, article_type, article_content, article_author) " +
            "values (#{article_title}, #{article_type}, #{article_content}, #{article_author})")
    void save(ClmsArticle clmsArticle);

    // 根 据 id 查 询
    @Select("select * from clms_article where article_id = #{id}")
    ClmsArticle getById(Integer id);

    // 根 据 id 删 除
    @Delete("delete from clms_article where article_id = #{id}")
    void deleteById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_article set article_id = #{article_id}\n" +
            "        <if test=\"article_title != null and article_title != ''\">\n" +
            "            ,article_title = #{article_title}\n" +
            "        </if>\n" +
            "        <if test=\"article_content != null and article_content != ''\">\n" +
            "            ,article_content = #{article_content}\n" +
            "        </if>\n" +
            "        <if test=\"article_author != null and article_author != ''\">\n" +
            "            ,article_author = #{article_author}\n" +
            "        </if>\n" +
            "        where article_id = #{article_id}" +
            "</script>")
    void update(ClmsArticle clmsArticle);

    // 分 页 查 询
    /*@Select("<script>" +
            "        select article_id, article_title, article_content, article_like, article_author, article_comment, create_time, update_time, article_type from clms_article\n" +
            "        where article_id = article_id \n" +
            "        <if test=\"params.article_id!=null\">\n" +
            "            and article_id = #{params.article_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.article_title!=null\">\n" +
            "            and article_title like CONCAT('%', #{params.article_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_type!=null\">\n" +
            "            and article_type like CONCAT('%', #{params.article_type}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_content!=null\">\n" +
            "            and article_content like CONCAT('%', #{params.article_content}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.article_like!=null\">\n" +
            "            and article_like = #{params.article_like}\n" +
            "        </if>\n" +
            "         <if test=\"params.article_author!=null\">\n" +
            "            and article_author like CONCAT('%', #{params.article_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_comment!=null\">\n" +
            "            and article_comment like CONCAT('%', #{params.article_comment}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.create_time!=null\">\n" +
            "            and create_time between #{params.create_time[0],jdbcType=TIMESTAMP} and #{params.create_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.update_time!=null\">\n" +
            "            and update_time between #{params.update_time[0],jdbcType=TIMESTAMP} and #{params.update_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.article_title!=null\">\n" +
            "            and article_title like CONCAT('%', #{params.article_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsArticle> getByPage(Page<ClmsArticle> page);*/

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_article\n" +
            "        where article_id = article_id \n" +
            "        <if test=\"params.article_id!=null\">\n" +
            "            and article_id = #{params.article_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.article_title!=null\">\n" +
            "            and article_title like CONCAT('%', #{params.article_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_type!=null\">\n" +
            "            and article_type like CONCAT('%', #{params.article_type}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_content!=null\">\n" +
            "            and article_content like CONCAT('%', #{params.article_content}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.article_like!=null\">\n" +
            "            and article_like = #{params.article_like}\n" +
            "        </if>\n" +
            "         <if test=\"params.article_author!=null\">\n" +
            "            and article_author like CONCAT('%', #{params.article_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_comment!=null\">\n" +
            "            and article_comment like CONCAT('%', #{params.article_comment}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.create_time!=null\">\n" +
            "            and create_time between #{params.create_time[0],jdbcType=TIMESTAMP} and #{params.create_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.update_time!=null\">\n" +
            "            and update_time between #{params.update_time[0],jdbcType=TIMESTAMP} and #{params.update_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsArticle> page);

    //分页查询
    @Select("<script>"+
            "       select article_id, article_title, article_content, article_like, article_comment, create_time, update_time, user_name, type_name, article_type\n"+
            "       from clms_article inner join clms_user on clms_article.article_author = clms_user.user_id\n"+
            "                         inner join clms_type on clms_article.article_type = clms_type.type_id"+
            "         where article_id = article_id \n" +
            "        <if test=\"params.article_id!=null\">\n" +
            "            and article_id = #{params.article_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.article_title!=null\">\n" +
            "            and article_title like CONCAT('%', #{params.article_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.type_name!=null\">\n" +
            "            and type_name like CONCAT('%', #{params.type_name}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_content!=null\">\n" +
            "            and article_content like CONCAT('%', #{params.article_content}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.article_like!=null\">\n" +
            "            and article_like = #{params.article_like}\n" +
            "        </if>\n" +
            "         <if test=\"params.user_name!=null\">\n" +
            "            and user_name like CONCAT('%', #{params.user_name}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_comment!=null\">\n" +
            "            and article_comment like CONCAT('%', #{params.article_comment}, '%')\n" +
            "        </if>\n" +
            "         <if test=\"params.create_time!=null\">\n" +
            "            and create_time between #{params.create_time[0],jdbcType=TIMESTAMP} and #{params.create_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.update_time!=null\">\n" +
            "            and update_time between #{params.update_time[0],jdbcType=TIMESTAMP} and #{params.update_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}"+
            "</script>")
    List<ClmsArticle> getByPage(Page<ClmsArticle> page);

    //获取用户文章类型信息
    @Select("<script>" +
            "        select user_name," +
            "        count(article_author) article_count," +
            "        sum(article_like) like_count," +
            "        sum(article_comment) comment_count\n" +
            "        from clms_article a,clms_user u\n" +
            "        where a.is_deleted = 0 and a.article_author = u.user_id \n" +
            "        group by u.user_name \n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Map> getArticleCountInfo(Page<ArticleStatistics> page);

    //查询用户文章排行
    @Select("<script>" +
            "        select t.type_name," +
            "        count(a.article_type) article_count" +
            "        from clms_article a left join clms_type t on a.article_type = t.type_id\n" +
            "        where a.is_deleted = 0 and a.article_author = #{params.articleAuthor}\n" +
            "        group by a.article_type \n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by count(a.${sortColumn}) ${sortMethod}\n" +
            "        </if>\n" +
            "</script>")
    List<Map> getUserArticleCountInfo(Page<ArticleStatistics> page);

}