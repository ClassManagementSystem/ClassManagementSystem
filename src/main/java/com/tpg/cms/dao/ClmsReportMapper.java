package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsReportMapper {

    //通过报告id查询
    @Select("select * from clms_report where report_id = #{id}")
    ClmsReport getById(Integer id);

    // 通 过 用 户 id 查 询
    @Select("select * " +
            "from clms_report " +
            "where report_id=(" +
            "select report_id " +
            "from clms_user_report " +
            "where user_id=#{user_id})")
    ClmsReport getByUserId(Integer user_id);

    // 分 页 查 询
    @Select("<script>" +
            "        select * from clms_report" +
            "        where 1=1" +
            "        <if test=\"params.report_type!=null and params.report_type!=''\">" +
            "            and report_type like CONCAT('%', #{params.report_type}, '%')" +
            "        </if>" +
            "        <if test=\"params.report_content!=null and params.report_content!=''\">" +
            "            and report_content like CONCAT('%', #{params.report_content}, '%')" +
            "        </if>" +
            "        <if test=\"params.create_time!=null\">" +
            "            and create_time between #{params.create_time[0]} and #{params.create_time[1]}" +
            "        </if>" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsReport> getByPage(Page<ClmsReport> page);

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_report\n" +
            "</script>")
    int getCountByPage(Page<ClmsReport> page);

    //改变批阅状态
    @Update("update clms_report set is_checked = #{check} where report_id = #{id}")
    void changeCheck(Integer id, int check);
}