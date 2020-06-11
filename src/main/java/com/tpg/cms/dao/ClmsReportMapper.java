package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsQuestion;
import com.tpg.cms.model.ClmsReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

    //通过用户id查询
    @Select("select * " +
            "from clms_report " +
            "where report_id=(" +
                "select report_id " +
                "from clms_user_report " +
                "where user_id=#{user_id})")
    ClmsReport getByUserId(Integer user_id);
}