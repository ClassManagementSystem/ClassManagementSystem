package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsComment;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsCommentMapper {

    // 根 据 id 删 除
    @Delete("delete from clms_comment where comment_id = #{id}")
    void deleteById(Integer id);

    //分页查询
    @Select("<script>" +
            "        select comment_id, user_name, article_id, comment_pid, comment_content, comment_like, comment_time\n"+
            "        from clms_comment inner join clms_user on clms_comment.comment_user = clms_user.user_id\n"+
            "        where 1 = 1\n"+
            "        <if test=\"params.comment_id!=null\">\n" +
            "            and comment_id = #{params.comment_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.user_name!=null\">\n" +
            "            and user_name like CONCAT('%', #{params.user_name}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_id!=null\">\n" +
            "            and article_id = #{params.article_id}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_pid!=null\">\n" +
            "            and comment_pid = #{params.comment_pid}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_content!=null\">\n" +
            "            and comment_content  like CONCAT('%', #{params.comment_content}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.comment_like!=null\">\n" +
            "            and comment_like = #{params.comment_like}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_time!=null\">\n" +
            "            and comment_time between #{params.comment_time[0],jdbcType=TIMESTAMP} and #{params.comment_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsComment> getByPage(Page<ClmsComment> page);

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_comment\n" +
            "        where 1 = 1 \n" +
            "         <if test=\"params.comment_id!=null\">\n" +
            "            and comment_id = #{params.comment_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.comment_user!=null\">\n" +
            "            and comment_user like CONCAT('%', #{params.comment_user}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.article_id!=null\">\n" +
            "            and article_id = #{params.article_id}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_pid!=null\">\n" +
            "            and comment_pid = #{params.comment_pid}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_content!=null\">\n" +
            "            and comment_content like CONCAT('%', #{params.comment_content}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.comment_like!=null\">\n" +
            "            and comment_like = #{params.comment_like}\n" +
            "        </if>\n" +
            "         <if test=\"params.comment_time!=null\">\n" +
            "            and comment_time between #{params.comment_time[0],jdbcType=TIMESTAMP} and #{params.comment_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsComment> page);

}