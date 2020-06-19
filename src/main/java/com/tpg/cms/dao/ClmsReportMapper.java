package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsAnswer;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.model.ClmsReport;
import com.tpg.cms.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClmsReportMapper {
    int deleteByPrimaryKey(Integer reportId);

    int insert(ClmsReport record);

    int insertSelective(ClmsReport record);

    ClmsReport selectByPrimaryKey(Integer reportId);

    int updateByPrimaryKeySelective(ClmsReport record);

    int updateByPrimaryKey(ClmsReport record);

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
            "        select * from clms_report\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<ClmsReport> getByPage(Page<ClmsReport> page);

    // 统 计 数 据 总 数
    @Select("<script>" +
            "        select count(*) from  clms_report\n" +
            "</script>")
    int getCountByPage(Page<ClmsReport> page);
}