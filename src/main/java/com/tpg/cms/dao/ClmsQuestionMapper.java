package com.tpg.cms.dao;


import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsQuestionMapper {



    // 保 存 新 增
    @Insert("insert into clms_question(question_title, question_content, question_author) " +
            "values (#{question_title}, #{question_content}, #{question_author})")
    void save(ClmsQuestion clmsQuestion);

    // 根 据 id 删 除
    @Update("update clms_question set question_status = 1 where question_id = #{id}")
    void deletedById(Integer id);

    // 根 据 id 恢 复 已 删 除 的
    @Update("update clms_question set question_status = 0 where question_id = #{id}")
    void  restoreById(Integer id);

    //
    @Update("<script>" +
            "        update clms_question set question_id = #{question_id}\n" +
            "        <if test=\"question_title!=null and question_title!=''\">\n" +
            "            ,question_title = #{question_title}\n" +
            "        </if>\n" +
            "        <if test=\"question_content != null and question_content != ''\">\n" +
            "            ,question_content = #{question_content}\n" +
            "        </if>\n" +
            "        <if test=\"question_author!=null and question_author!=''\">\n" +
            "            ,question_author = #{question_author}\n" +
            "        </if>\n" +
            "        <if test=\"question_mark!=null\">\n" +
            "            ,question_mark = #{question_mark}\n" +
            "        </if>\n" +
            "        where question_id = #{question_id}" +
            "</script>")
    void update(ClmsQuestion clmsQuestion);

    // 改 变 问 题 状 态 是 否 已 解 决
    @Update("update clms_question set question_mark = #{mark} where question_id = #{id}")
    void changeSolve(Integer id, int mark);

    // 更 新 问 题 的 答 复 数 量 ，通 过 id 确 定
    @Update("update clms_question set answer_count = #{aCount} where question_id = #{id}")
    void updateAnswerCount(Integer id, Integer aCount);

    // 获 取 问 题 的 未 删 除 的 答 复 数 量 ，通 过 id 确 定
    @Select("select count(*) from clms_answer where question_id = #{qid} and answer_status = 0 ")
    int getAnswerCount(Integer qid);

    // 通 过 id 查 询
    @Select("select * from clms_question where question_id = #{id}")
    ClmsQuestion getById(Integer id);

    // 通 过 id 查 询 --- 查 询 未 删 除 的 --- 预 留 给 前 台 的 接 口
    @Select("select * from clms_question where question_status = 0 and question_id = #{id}")
    ClmsQuestion getNotDeletedById(Integer id);

    // 根 据 条 件 查 询 问 题 总 数
    @Select("<script>" +
            "        select count(*) from clms_question\n" +
            "        where question_id = question_id\n" +
            "        <if test=\"params.question_title!=null and params.question_title!=''\">\n" +
            "            and question_title like CONCAT('%', #{params.question_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.question_author!=null and params.question_author!=''\">\n" +
            "            and question_author like CONCAT('%', #{params.question_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.question_time!=null\">\n" +
            "            and question_time between #{params.question_time[0]} and #{params.question_time[1]}\n" +
            "        </if>\n" +
            "        <if test=\"params.question_mark!=null\">\n" +
            "            and question_mark = #{params.question_mark}\n" +
            "        </if>\n" +
            "        <if test=\"params.question_status!=null\">\n" +
            "            and question_status = #{params.question_status}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsQuestion> page);

    //分 页 查 询
    @Select("<script>" +
            "        select question_id, question_title, question_content, question_author, question_time, update_time, question_good, answer_count, question_mark,question_status from clms_question\n" +
            "        where question_id = question_id \n" +
            "        <if test=\"params.question_title!=null and params.question_title!=''\">\n" +
            "            and question_title like CONCAT('%', #{params.question_title}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.question_author!=null and params.question_author!=''\">\n" +
            "            and question_author like CONCAT('%', #{params.question_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.question_time!=null\">\n" +
            "            and question_time between #{params.question_time[0]} and #{params.question_time[1]}\n" +
            "        </if>\n" +
            "        <if test=\"params.question_mark!=null\">\n" +
            "            and question_mark = #{params.question_mark}\n" +
            "        </if>\n" +
            "        <if test=\"params.question_status!=null\">\n" +
            "            and question_status = #{params.question_status}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsQuestion> getByPage(Page<ClmsQuestion> page);
}
