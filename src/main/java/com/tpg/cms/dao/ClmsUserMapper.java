package com.tpg.cms.dao;

import com.tpg.cms.model.ClmsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ClmsUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(ClmsUser record);

    int insertSelective(ClmsUser record);

    ClmsUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ClmsUser record);

    int updateByPrimaryKey(ClmsUser record);
}