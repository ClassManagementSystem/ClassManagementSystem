package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsUserReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClmsUserReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClmsUserReport record);

    int insertSelective(ClmsUserReport record);

    ClmsUserReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClmsUserReport record);

    int updateByPrimaryKey(ClmsUserReport record);
}