package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsAnswerMapper {

    // 新 增  @param clmsAnswer
    @Insert("insert into clms_answer(question_id, answer_content, answer_author, answer_mark) " +
            "values (#{question_id}, #{answer_content}, #{answer_author}, #{answer_mark})")
    void save(ClmsAnswer clmsAnswer);

    // 根 据 id 查 询
    @Select("select * from clms_answer where answer_status = 0 and answer_id = #{id}")
    ClmsAnswer getById(Integer id);

    // 根 据 id 删 除
    @Update("update clms_answer set answer_status = 1 where answer_id = #{id}")
    void deletedById(Integer id);

    // 更 新 数 据
    @Update("<script>" +
            "        update clms_answer set answer_id = #{answer_id}\n" +
            "        <if test=\"question_id != null and question_id != ''\">\n" +
            "            ,question_id = #{question_id}\n" +
            "        </if>\n" +
            "        <if test=\"answer_content !=n ull and answer_content != ''\">\n" +
            "            ,answer_content = #{answer_content}\n" +
            "        </if>\n" +
            "        <if test=\"answer_author != null and answer_author != ''\">\n" +
            "            ,answer_author = #{answer_author}\n" +
            "        </if>\n" +
            "        <if test=\"answer_mark!=null\">\n" +
            "            ,answer_mark = #{answer_mark}\n" +
            "        </if>\n" +
            "        where answer_id = #{answer_id}" +
            "</script>")
    void update(ClmsAnswer clmsAnswer);

    // 分 页 查 询
    @Select("<script>" +
            "        select answer_id, question_id, answer_content, answer_good, answer_time, update_time, answer_author, answer_mark from cl_answer\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.question_id!=null\">\n" +
            "            and question_id = #{params.question_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_content!=null and params.answer_content!=''\">\n" +
            "            and answer_content like CONCAT('%', #{params.answer_content}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_author!=null and params.answer_author!=''\">\n" +
            "            and answer_author like CONCAT('%', #{params.answer_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_time!=null\">\n" +
            "            and answer_time between #{params.answer_time[0]} and #{params.answer_time[1]}\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_mark!=null\">\n" +
            "            and answer_mark = #{params.answer_mark}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsAnswer> getByPage(Page<ClmsAnswer> page);

    // 统 计 answer_status = 0 的 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_answer\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.question_id!=null\">\n" +
            "            and question_id = #{params.question_id}\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_content!=null and params.answer_content!=''\">\n" +
            "            and answer_content like CONCAT('%', #{params.answer_content}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_author!=null and params.answer_author!=''\">\n" +
            "            and answer_author like CONCAT('%', #{params.answer_author}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_time!=null\">\n" +
            "            and answer_time between #{params.answer_time[0],jdbcType=TIMESTAMP} and #{params.answer_time[1],jdbcType=TIMESTAMP}\n" +
            "        </if>\n" +
            "        <if test=\"params.answer_mark!=null\">\n" +
            "            and answer_mark = #{params.answer_mark}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<ClmsAnswer> page);

    // 更 改 答 复 状 态
    @Update("update cl_answer set answer_mark = #{mark} where answer_id = #{id}")
    void changeAdopt(Integer id, int mark);

}
